package com.study.ally.presentation.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->

        AppNavHost(
            navController = navController,
            padding = padding
        )
    }
}