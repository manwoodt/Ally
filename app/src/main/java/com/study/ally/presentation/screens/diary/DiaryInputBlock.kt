package com.study.ally.presentation.screens.diary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DiaryInputBlock(
    eventsText: String,
    onEventsTextChange: (String) -> Unit,
    medicationsText: String,
    onMedicationsTextChange: (String) -> Unit,
    onAddClick: (String) -> Unit,
) {

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            OutlinedTextField(
                value = eventsText,
                onValueChange = onEventsTextChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Предшествующие события") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = medicationsText,
                onValueChange = onMedicationsTextChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Принятые препараты") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    // Объединяем оба поля в одну строку
                    val combined = listOf(eventsText, medicationsText)
                        .filter { it.isNotBlank() }
                        .joinToString("; ")
                    onAddClick(combined)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Добавить запись")
            }
        }
    }
}