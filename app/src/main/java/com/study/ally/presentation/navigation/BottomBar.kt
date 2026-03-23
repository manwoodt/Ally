package com.study.ally.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController) {

    val currentBackStack = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack.value?.destination?.route

    NavigationBar {

        Screen.bottomBarItems.forEach { screen ->

            NavigationBarItem(
                selected = currentRoute == screen.route,

                onClick = {
                    navController.navigate(screen.route) {

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.contentDescription
                    )
                },

                label = {
                    Text(screen.title)
                }
            )
        }
    }
}