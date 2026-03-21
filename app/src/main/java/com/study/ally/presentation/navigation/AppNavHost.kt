package com.study.ally.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.study.ally.presentation.screens.diary.DiaryScreen
import com.study.ally.presentation.screens.passport.PassportScreen
import com.study.ally.presentation.screens.symptoms.SymptomsScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.PASSPORT) {

        composable(Routes.PASSPORT) {
            PassportScreen()
        }

        composable(Routes.DIARY) {
            DiaryScreen()
        }

        composable(Routes.SYMPTOMS) {
            SymptomsScreen()
        }
    }
}