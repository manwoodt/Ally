package com.study.ally.presentation.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val contentDescription: String
) {

    object Passport : Screen(
        route = "passport",
        title = "Паспорт",
        icon = Icons.Default.Favorite,
        contentDescription = "Медицинский паспорт"
    )

    object Diary : Screen(
        route = "diary",
        title = "Дневник",
        icon = Icons.Default.Edit,
        contentDescription = "Дневник триггеров"
    )

    object Symptoms : Screen(
        route = "symptoms",
        title = "Симптомы",
        icon = Icons.Default.Warning,
        contentDescription = "Симптомы аллергии"
    )

    companion object {
        val bottomBarItems = listOf(
            Passport,
            Diary,
            Symptoms
        )
    }
}