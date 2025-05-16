package com.group.bottombarview.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.group.bottombarview.ui.theme.BottomBarViewTheme
import com.group.bottombarview.ui.screen.Greeting

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomBarViewTheme {
                Greeting()
            }
        }
    }
}
