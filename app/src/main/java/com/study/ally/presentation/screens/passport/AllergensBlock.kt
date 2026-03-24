package com.study.ally.presentation.screens.passport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.study.ally.ui.theme.Primary
import com.study.ally.ui.theme.PrimaryContainer

@Composable
fun AllergensBlock(
    allergens: List<String>,
    isEditing: Boolean,
    onAdd: (String) -> Unit,
) {

    var input by remember { mutableStateOf("") }

    Column {

        Text("🚨 Аллергены", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(6.dp))

        Row() {
            allergens.forEach {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = PrimaryContainer
                    )
                ) {
                    Text(it, Modifier.padding(12.dp))
                }

                Spacer(Modifier.width(6.dp))
            }
        }

        if (isEditing) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.weight(1f),
                    label = { Text("Добавить аллерген") }
                )

                Button(onClick = {
                    if (input.isNotBlank()) {
                        onAdd(input)
                        input = ""
                    }
                }) {
                    Text("+")
                }
            }
        }
    }
}