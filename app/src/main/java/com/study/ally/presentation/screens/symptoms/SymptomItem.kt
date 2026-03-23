package com.study.ally.presentation.screens.symptoms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.study.ally.domain.model.Symptom
import com.study.ally.presentation.utils.toIntensityText
import com.study.ally.presentation.utils.toReadableTime

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