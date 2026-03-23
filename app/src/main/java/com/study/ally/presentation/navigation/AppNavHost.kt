package com.study.ally.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.study.ally.presentation.screens.diary.DiaryScreen
import com.study.ally.presentation.screens.passport.PassportScreen
import com.study.ally.presentation.screens.symptoms.SymptomsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    padding: PaddingValues,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Passport.route,
        modifier = Modifier.padding(padding)
    ) {

        composable(Screen.Passport.route) {
            PassportScreen()
        }

        composable(Screen.Diary.route) {
            DiaryScreen()
        }

        composable(Screen.Symptoms.route) {
            SymptomsScreen()
        }
    }
}