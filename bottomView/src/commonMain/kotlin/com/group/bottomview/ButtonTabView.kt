package com.group.bottomview

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.bottomview.ConstantsUI.ICON_SIZE

@Composable
fun ButtonTabView(
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