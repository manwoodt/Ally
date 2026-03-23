package com.study.ally.presentation.screens.symptoms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.study.ally.domain.model.Symptom
import com.study.ally.presentation.utils.toIntensityText
import com.study.ally.presentation.utils.toReadableTime
import org.koin.androidx.compose.koinViewModel

@Composable
fun SymptomsScreen(
    viewModel: SymptomsViewModel = koinViewModel(),
) {

    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Симптомы",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        val availableSymptoms = listOf(
            "Чихание",
            "Зуд",
            "Слезы",
            "Кашель"
        )

        availableSymptoms.forEach { name ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        viewModel.onLongClick(name)
                    },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Text(
                    text = name,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        if (state.showSlider) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Интенсивность: ${state.intensity.toIntensityText()}",
                style = MaterialTheme.typography.titleMedium
            )

            Slider(
                value = state.intensity,
                onValueChange = viewModel::onIntensityChange,
                valueRange = 1f..3f,
                steps = 1
            )

            Button(
                onClick = viewModel::save,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Сохранить")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "История",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        state.symptoms
            .asReversed()
            .forEach { symptom ->
                SymptomItem(symptom = symptom)
            }
    }
}
@Composable
fun SymptomItem(symptom: Symptom) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(
                text = symptom.name,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Интенсивность: ${symptom.intensity.toIntensityText()}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = symptom.timeOfDay,
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = symptom.timestamp.toReadableTime(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
