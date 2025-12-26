package com.group.bottomview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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