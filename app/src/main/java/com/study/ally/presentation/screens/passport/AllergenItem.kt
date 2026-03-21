package com.study.ally.presentation.screens.passport

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.study.ally.domain.model.Allergen

@Composable
fun AllergenItem(allergen: Allergen) {

    val color = if (allergen.isDangerous)
        Color(0xFFFFCDD2)
    else
        Color(0xFFC8E6C9)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .padding(16.dp)
    ) {

        Text(
            text = allergen.name,
            modifier = Modifier.weight(1f)
        )

        if (allergen.isDangerous) {
            Text("⚠️ Опасно")
        }
    }
}