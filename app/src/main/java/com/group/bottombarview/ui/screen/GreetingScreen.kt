package com.group.bottombarview.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.group.bottombarview.ui.theme.BottomBarViewTheme
import com.group.bottombarview.ui.view.CustomBottomNavScreen

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

    CustomBottomNavScreen()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomBarViewTheme {
        Greeting("Android")
    }
}