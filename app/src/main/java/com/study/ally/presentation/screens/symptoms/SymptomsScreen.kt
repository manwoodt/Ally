package com.study.ally.presentation.screens.symptoms

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun SymptomsScreen(
    viewModel: SymptomsViewModel = koinViewModel()
) {

    var showSlider by remember { mutableStateOf(false) }
    var intensity by remember { mutableFloatStateOf(1f) }

    Column(Modifier.padding(16.dp)) {

        Text(
            "Чихание",
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {},
                    onLongClick = { showSlider = true }
                )
        )

        if (showSlider) {

            Slider(
                value = intensity,
                onValueChange = { intensity = it },
                valueRange = 1f..3f,
                steps = 1
            )

            Button(onClick = {
                viewModel.addSymptom("Чихание", intensity.toInt())
                showSlider = false
            }) {
                Text("Сохранить")
            }
        }
    }
}