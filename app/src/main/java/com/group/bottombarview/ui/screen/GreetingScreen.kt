package com.group.bottombarview.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.group.bottombarview.ui.theme.BottomBarViewTheme
import com.group.bottombarview.ui.view.BottomButtonModel
import com.group.bottombarview.ui.view.CustomBottomNavigationBar

sealed class Screen(val route: String, val model: BottomButtonModel) {
    data object Home :
        Screen("home", BottomButtonModel(Icons.Filled.Home, "Home", Color.Black, Color.Gray))

    data object Search :
        Screen("search", BottomButtonModel(Icons.Filled.Search, "Search", Color.Black, Color.Gray))

    data object Profile : Screen(
        "profile",
        BottomButtonModel(Icons.Filled.Person, "Profile", Color.Black, Color.Gray)
    )

    data object HomeWithOutText :
        Screen("home", BottomButtonModel(Icons.Filled.Home, "", Color.Black, Color.Gray))

    data object SearchWithOutText :
        Screen("search", BottomButtonModel(Icons.Filled.Search, "", Color.Black, Color.Gray))

    data object ProfileWithOutText : Screen(
        "profile",
        BottomButtonModel(Icons.Filled.Person, "", Color.Black, Color.Gray)
    )
}



@Composable
fun Greeting() {
    val navController = rememberNavController()
    val screens = listOf(Screen.Home, Screen.Search, Screen.Profile)
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            CustomBottomNavigationBar(
                screens = screens,
                selectedIndex = selectedIndex,
                onItemSelected = { index ->
                    selectedIndex = index
                    val screen = screens[index]

                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = screens[selectedIndex].route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Search.route) { SearchScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Головна")
    }
}

@Composable
fun SearchScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Пошук")
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Профіль")
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomBarViewTheme {
        Greeting()
    }
}