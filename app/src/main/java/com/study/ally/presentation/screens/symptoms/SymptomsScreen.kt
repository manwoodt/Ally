package com.study.ally.presentation.screens.symptoms

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun SymptomsScreen(
    viewModel: SymptomsViewModel = koinViewModel(),
) {

    val state by viewModel.uiState.collectAsState()

    Column(Modifier.padding(16.dp)) {

        Text(
            "Чихание",
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {},
                    onLongClick = { viewModel.onLongClick("Чихание") }
                )
        )

        if (state.showSlider) {

            Slider(
                value = state.intensity,
                onValueChange = { value ->
                    viewModel.onIntensityChange(value)
                },
                valueRange = 1f..3f,
                steps = 1
            )

            Button(onClick = viewModel::save) {
                Text("Сохранить")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        state.symptoms.forEach {
            Text("${it.name} - ${it.intensity}")
        }
    }
}