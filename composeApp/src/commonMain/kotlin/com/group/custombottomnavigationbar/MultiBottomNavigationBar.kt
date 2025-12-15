package com.group.custombottomnavigationbar

import OutlineFile
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.group.bottomview.ButtonTab
import com.group.bottomview.Colors
import com.group.bottomview.ConstantsUI.PARENT_CIRCLE_SIZE
import com.group.bottomview.TabsModel
import com.group.bottomview.drawable.HomeIcon
import com.group.bottomview.drawable.OutlineChat
import com.group.bottomview.drawable.OutlineZephyr
import com.group.bottomview.drawable.Person
import com.group.bottomview.drawable.Search
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview

data class MultiTabs(
    val tab: List<TabsModel>,
    var selectedIndex: Int,
) {
    companion object {
        fun getMultiTabListWithText(): List<MultiTabs> {
            val tab1 = MultiTabs(listOf(TabsModel(HomeIcon, "Home")), 0)
            val tab2 = MultiTabs(
                listOf(
                    TabsModel(OutlineChat, "Chat 1"),
                    TabsModel(OutlineFile, "Chat 2"),
                    TabsModel(OutlineZephyr, "Chat 3")
                ), 0
            )
            val tab3 = MultiTabs(listOf(TabsModel(Search, "Search")), 0)
            val tab4 = MultiTabs(listOf(TabsModel(Person, "Profile")), 0)
            return listOf(tab1, tab2, tab3, tab4)
        }
    }
}

@Composable
fun MultiBottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    tabs: List<MultiTabs> = MultiTabs.getMultiTabListWithText(),
    colors: Colors = Colors.getDefaultColors(),
    enableAnimation: Boolean = true,
    budgets: State<Map<Int, Int>>? = null,
    onItemSelected: (Int) -> Unit = {},
) {
    var showCircle by rememberSaveable  { mutableStateOf(false) }
    var isVisible by rememberSaveable  { mutableStateOf(false) }
    var tabsState by rememberSaveable  { mutableStateOf(tabs.toMutableList()) }
    var selectedIndex by rememberSaveable  { mutableIntStateOf(selectedIndex) }

    var selectedMultipleTabs by remember  { mutableStateOf<MultiTabs?>(null) }
    var moveToX by remember { mutableStateOf<Float?>(null) }
    var moveToY by remember { mutableStateOf<Float?>(null) }
    val circleX = remember { Animatable(0f) }

    val columnPositionsX = remember { mutableStateListOf<Float>() }
    val circleSizeVisible = 16
    val circleSize by animateDpAsState(
        targetValue = if (isVisible) circleSizeVisible.dp else 0.dp,
        label = ""
    )

    if (enableAnimation) {
        LaunchedEffect(Unit) {
            val defaultX = columnPositionsX[selectedIndex]
            if (circleX.targetValue == 0f && defaultX != 0f) {
                circleX.animateTo(targetValue = defaultX, animationSpec = tween(10))
            }
        }

        LaunchedEffect(moveToX) {
            val target = moveToX ?: return@LaunchedEffect

            isVisible = true

            circleX.animateTo(
                targetValue = target,
                animationSpec = tween(600)
            )

            isVisible = false

            if (selectedMultipleTabs != null) {
                showCircle = true
            }
        }
    }

    Column {

        if (showCircle) {
            Box(modifier = Modifier.offset {
                val x = circleX.value.toInt() - PARENT_CIRCLE_SIZE
                val y = (moveToY?.toInt() ?: 0) - PARENT_CIRCLE_SIZE / 2
                IntOffset(x, y)
            }) {
                DonutTabs(
                    tabs = selectedMultipleTabs?.tab ?: listOf(),
                    selectedTabIndex = selectedMultipleTabs?.selectedIndex ?: 0,
                    onTabSelected = { index ->
                        val selectedIcon = tabsState[selectedIndex].tab[index].icon
                        val newList = mutableListOf<MultiTabs>()
                        tabsState.forEach { tabs ->
                            val newIcon = tabs.tab.find { it.icon == selectedIcon }
                            val selectedIndex = if (newIcon != null) {
                                index
                            } else {
                                0
                            }
                            newList.add(MultiTabs(tabs.tab, selectedIndex))
                        }
                        tabsState = newList
                        selectedMultipleTabs = null
                        showCircle = false
                    }
                )
            }
        }

        Box(
            modifier = modifier
                .padding(
                    bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                )
                .fillMaxWidth()
                .background(colors.bacGroundColor)
                .padding(3.dp),
        ) {

            if (enableAnimation) {
                Box(
                    modifier = Modifier
                        .offset {
                            val x = circleX.value.toInt()
                            val y = moveToY?.toInt() ?: 0
                            IntOffset(x, y)
                        }
                        .size(circleSize)
                        .clip(CircleShape)
                        .background(colors.selectCircleColor.copy(alpha = if (isVisible) 1f else 0f))
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                tabsState.forEachIndexed { index, multiTabModel ->
                    val buttons = multiTabModel.tab
                    val button = buttons[multiTabModel.selectedIndex]

                    ButtonTab(
                        Modifier.onGloballyPositioned { layout ->
                            if (enableAnimation) {
                                val centerX = layout.boundsInParent().center.x - circleSizeVisible
                                if (columnPositionsX.size > index) {
                                    columnPositionsX[index] = centerX
                                } else {
                                    columnPositionsX.add(centerX)
                                }

                                val centerY =
                                    layout.boundsInParent().center.y / 2// for center column
                                moveToY =
                                    if (button.title.isNotEmpty()) centerY / 2 else centerY  // for center top part column (image)
                            }
                        },
                        isVisible = isVisible,
                        index = index,
                        selectedIndex = selectedIndex,
                        icon = button.icon,
                        title = button.title,
                        colors = colors,
                        budgetsValue = budgets?.value[index] ?: 0,
                        onClick = {
                            selectedMultipleTabs = null
                            showCircle = false

                            if (selectedIndex != index) {
                                selectedIndex = index

                                if (enableAnimation) {
                                    val x = columnPositionsX.getOrNull(index)
                                    x?.let {
                                        moveToX = x
                                    }

                                    isVisible = true
                                } else {
                                    selectedMultipleTabs = multiTabModel
                                    showCircle = true
                                }

                                if (buttons.size > 1) {
                                    selectedMultipleTabs = multiTabModel
                                }

                                onItemSelected.invoke(it)
                            } else if (tabsState[selectedIndex].tab.size > 1) {
                                selectedMultipleTabs = multiTabModel
                                showCircle = true
                            }
                        },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val map = MutableStateFlow(mapOf(1 to 11))
    MultiBottomNavigationBar(
        budgets = map.asStateFlow().collectAsState()
    )
}
