package com.group.bottombarview.ui.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomBottomNavigationBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf("Home", "Search", "Profile")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Person)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(
                Color.DarkGray,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = index == selectedItem
            val scale by animateFloatAsState(
                if (isSelected) 1.2f else 1.0f,
                animationSpec = tween(300)
            )
            val iconColor by animateColorAsState(
                if (isSelected) Color.White else Color.Gray,
                animationSpec = tween(300)
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemSelected(index) }
                    .scale(scale),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icons[index],
                    contentDescription = item,
                    tint = iconColor,
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = item,
                    color = iconColor,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun CustomBottomNavScreen() {
    var selectedIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1f)) // Контент зверху
        CustomBottomNavigationBar(
            selectedItem = selectedIndex,
            onItemSelected = { selectedIndex = it }
        )
        Spacer(modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars))
    }
}
