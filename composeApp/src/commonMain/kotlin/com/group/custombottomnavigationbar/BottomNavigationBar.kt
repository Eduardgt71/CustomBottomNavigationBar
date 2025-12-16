package com.group.custombottomnavigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.bottomview.ConstantsUI.ICON_SIZE
import com.group.bottomview.drawable.HomeIcon
import com.group.bottomview.drawable.Person
import com.group.bottomview.drawable.Search
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview



@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    tabs: List<TabsModel>,
    colors: Colors = Colors.getDefaultColors(),
    enableAnimation: Boolean = true,
    budgets: State<Map<Int, Int>>? = null,
    onItemSelected: (Int) -> Unit = {},
) {
    val multiTabs = tabs.toMultiTabs()
    BottomNavigationBar(
        modifier = modifier,
        selectedIndex = selectedIndex,
        tabs = multiTabs,
        colors = colors,
        enableAnimation = enableAnimation,
        budgets = budgets,
        onItemSelected = onItemSelected
    )
}

private fun List<TabsModel>.toMultiTabs(): MutableList<MultiTabs> {
val result = mutableListOf<MultiTabs>()
    forEach { tab->
        result.add(MultiTabs(listOf(tab), 0))
    }
    return result
}

@Composable
fun ButtonTab(
    modifier: Modifier,
    index: Int,
    selectedIndex: Int,
    icon: ImageVector,
    title: String,
    colors: Colors,
    isVisible: Boolean,
    budgetsValue: Int,
    onClick: (Int) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable {
                onClick(index)
            }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column {
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
                        modifier = Modifier.size(ICON_SIZE.dp)
                    )
                }
            }
            if (title.isNotEmpty()) {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    color = if (selectedIndex == index) colors.selectedTextColor else colors.unSelectedTextColor,
                )
            }
        }

        if (budgetsValue != 0) {
            BadgedBox(
                modifier = Modifier.align(Alignment.TopEnd),
                badge = {
                    Badge { Text(budgetsValue.toString()) }
                }
            ) {}
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CustomBottomNavigationBarPreview() {
    val map = MutableStateFlow(mapOf(1 to 11))
    BottomNavigationBar(
        budgets = map.asStateFlow().collectAsState(),
        tabs = TabsModel.getBaseTabListWithText()
    )
}

@Preview(showBackground = true)
@Composable
private fun BottomBarViewThemeWithEmptyTextPreview() {
    BottomNavigationBar(
        tabs = TabsModel.getBaseTabListWithOutText(),
    )
}