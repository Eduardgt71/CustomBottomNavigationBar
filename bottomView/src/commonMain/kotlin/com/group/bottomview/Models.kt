package com.group.bottomview

import OutlineFile
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.group.bottomview.drawable.HomeIcon
import com.group.bottomview.drawable.OutlineChat
import com.group.bottomview.drawable.OutlineZephyr
import com.group.bottomview.drawable.Person
import com.group.bottomview.drawable.Search

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