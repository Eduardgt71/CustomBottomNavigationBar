package com.group.custombottomnavigationbar

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    var selectedIndex by rememberSaveable  { mutableIntStateOf(0) }

    MaterialTheme {
        Scaffold(
            bottomBar = {
                MultiBottomNavigationBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = {
                        selectedIndex = it
                    }
                )
            }
        ) {

        }

    }
}