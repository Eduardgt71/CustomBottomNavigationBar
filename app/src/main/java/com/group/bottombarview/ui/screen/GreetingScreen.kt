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
import androidx.compose.ui.tooling.preview.Preview
import com.group.bottombarview.ui.theme.BottomBarViewTheme
import com.group.bottombarview.ui.view.CustomBottomNavigationBar

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val items = listOf("Home", "Search", "Profile")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Person)
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            CustomBottomNavigationBar(
                items = items,
                icons = icons,
                selectedIndex = selectedIndex,
                onItemSelected = { index ->
                    selectedIndex = index
                }
            )
        }
    ) { innerPadding ->
        // Вміст основного екрану
        Box(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Hello $name!",
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomBarViewTheme {
        Greeting("Android")
    }
}