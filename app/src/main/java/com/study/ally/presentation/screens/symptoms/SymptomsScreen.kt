package com.study.ally.presentation.screens.symptoms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.study.ally.presentation.utils.toIntensityText
import com.study.ally.ui.theme.Primary
import org.koin.androidx.compose.koinViewModel

private val symptomsList = listOf(
    "Чихание",
    "Заложенность носа",
    "Зуд",
    "Глазные проявления (слезотечение, покраснение)",
    "Кашель",
    "Першение в горле",
    "Удушье",
    "Отёки"
)

@OptIn(ExperimentalMaterial3Api::class)
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

        symptomsList.forEach { name ->
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
                steps = 1,
                thumb = {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                               .background(Primary)
                    )
                },
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


