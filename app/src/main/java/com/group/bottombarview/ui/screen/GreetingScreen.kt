package com.group.bottombarview.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.group.bottombarview.ui.theme.BottomBarViewTheme
import com.group.bottombarview.ui.view.BottomButtonModel
import com.group.bottombarview.ui.view.CustomBottomNavigationBar

@Composable
fun Greeting() {
    var selectedIndex by remember { mutableStateOf(0) }
    val buttons = listOf(
        BottomButtonModel(Icons.Filled.Home, "Home", Color.Black, Color.Gray),
        BottomButtonModel(Icons.Filled.Search, "Search", Color.Black, Color.Gray),
        BottomButtonModel(Icons.Filled.Person, "Profile", Color.Black, Color.Gray),
    )

    Scaffold(
        bottomBar = {
            CustomBottomNavigationBar(
                buttons = buttons,
                selectedIndex = selectedIndex,
                onItemSelected = { index ->
                    selectedIndex = index
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Hello World!",
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomBarViewTheme {
        Greeting()
    }
}