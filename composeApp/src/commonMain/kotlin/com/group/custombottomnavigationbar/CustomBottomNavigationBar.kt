package com.group.custombottomnavigationbar


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.custombottomnavigationbar.drawable.HomeIcon
import com.group.custombottomnavigationbar.drawable.Person
import com.group.custombottomnavigationbar.drawable.Search
import org.jetbrains.compose.ui.tooling.preview.Preview

data class TabsModel(
    val icon: ImageVector,
    val title: String,
) {
    companion object {
        fun getBaseTabListWithText(): List<TabsModel> {
            return listOf(
                TabsModel(HomeIcon, "Home"),
                TabsModel(Search, "Search"),
                TabsModel(Person, "Profile"),
            )
        }

        fun getBaseTabListWithOutText(): List<TabsModel> {
            return listOf(
                TabsModel(HomeIcon, ""),
                TabsModel(Search, ""),
                TabsModel(Person, ""),
            )
        }
    }
}

data class Colors(
    val bacGroundColor: Color,
    val selectCircleColor: Color,
    val selectedIconColor: Color,
    val unSelectedIconColor: Color,
    val selectedTextColor: Color,
    val unSelectedTextColor: Color,
) {
    companion object {
        @Composable
        fun getDefaultColors(): Colors {
            return Colors(
                bacGroundColor = Color.Blue,
                selectCircleColor = Color.Yellow,
                selectedIconColor = Color.Gray,
                unSelectedIconColor = Color.White,
                selectedTextColor = Color.Gray,
                unSelectedTextColor = Color.White
            )
        }
    }
}

@Composable
fun CustomBottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    tabs: List<TabsModel> = TabsModel.getBaseTabListWithText(),
    colors: Colors = Colors.getDefaultColors(),
    enableAnimation: Boolean = true,
    onItemSelected: (Int) -> Unit = {},
) {
    var selectedIndex by remember { mutableIntStateOf(selectedIndex) }

    var moveToX by remember { mutableStateOf<Float?>(null) }
    var moveToY by remember { mutableStateOf<Float?>(null) }
    val circleX = remember { Animatable(0f) }
    var isVisible by remember { mutableStateOf(false) }
    val columnPositionsX = remember { mutableStateListOf<Float>() }
    val circleSize by animateDpAsState(
        targetValue = if (isVisible) 16.dp else 0.dp,
        label = ""
    )

    if (enableAnimation) {
        LaunchedEffect(Unit) {
            val defaultX = columnPositionsX[0]
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
        }
    }


    Box(
        modifier = modifier
            .padding(
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            )
            .fillMaxWidth()
            .background(Color.Blue),
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
            tabs.forEachIndexed { index, button ->
                Button(
                    Modifier.onGloballyPositioned { layout ->
                        if (enableAnimation) {
                            val centerX = layout.boundsInParent().center.x
                            if (columnPositionsX.size > index) {
                                columnPositionsX[index] = centerX
                            } else {
                                columnPositionsX.add(centerX)
                            }

                            val centerY = layout.boundsInParent().center.y / 2 // for center column
                            moveToY = centerY / 2 // for center top part column (image)
                        }
                    },
                    isVisible = isVisible,
                    index = index,
                    selectedIndex = selectedIndex,
                    icon = button.icon,
                    title = button.title,
                    colors = colors,
                    onClick = {
                        selectedIndex = index

                        if (enableAnimation) {
                            val x = columnPositionsX.getOrNull(index)
                            x?.let {
                                moveToX = x
                            }

                            isVisible = true
                        }
                        onItemSelected.invoke(it)
                    },
                )
            }
        }
    }
}

@Composable
private fun Button(
    modifier: Modifier,
    index: Int,
    selectedIndex: Int,
    icon: ImageVector,
    title: String,
    colors: Colors,
    isVisible: Boolean,
    onClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onClick(index)
                },
        ) {
            val modifier = if (index == selectedIndex && !isVisible) {
                Modifier.background(
                    color = colors.selectCircleColor,
                    shape = RoundedCornerShape(16.dp)
                )
            } else {
                Modifier
            }
            Column(
                modifier = modifier
                    .padding(2.dp)
                    .padding(2.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = if (selectedIndex == index) colors.selectedIconColor else colors.unSelectedIconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        if (title.isNotEmpty()) {
            Spacer(Modifier.height(6.dp))

            Text(
                text = title,
                fontSize = 12.sp,
                color = if (selectedIndex == index) colors.selectedTextColor else colors.unSelectedTextColor,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CustomBottomNavigationBarPreview() {
    CustomBottomNavigationBar()
}

@Preview(showBackground = true)
@Composable
private fun BottomBarViewThemeWithEmptyTextPreview() {
    CustomBottomNavigationBar(
        tabs = TabsModel.getBaseTabListWithOutText(),
        selectedIndex = 0,
        onItemSelected = {}
    )
}