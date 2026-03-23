package com.study.ally.presentation.screens.passport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.study.ally.domain.model.Medicine

@Composable
fun MedicinesBlock(
    medicines: List<Medicine>,
    isEditing: Boolean,
    onAdd: (Medicine) -> Unit,
) {

    var name by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }

    Column {

            Text("💊 Препараты", style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(6.dp))


    Row() {
        medicines.forEach { med ->
            Card {
                Column(Modifier.padding(12.dp)) {
                    Text(med.name)
                    Text(
                        med.dosage,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(Modifier.width(6.dp))
        }
    }

        if (isEditing) {

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Название") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(6.dp))

            OutlinedTextField(
                value = dosage,
                onValueChange = { dosage = it },
                label = { Text("Дозировка") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(6.dp))

            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        onAdd(Medicine(name, dosage))
                        name = ""
                        dosage = ""
                    }
                }
            ) {
                Text("Добавить")
            }
        }
    }
}