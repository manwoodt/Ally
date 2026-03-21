package com.study.ally.presentation.screens.diary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel = koinViewModel()
) {

    var text by remember { mutableStateOf("") }
    val entries by viewModel.entries.collectAsState()

    Column(Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = text,
            onValueChange = { text = it }
        )

        Button(onClick = {
            viewModel.addEntry(text)
            text = ""
        }) {
            Text("Добавить")
        }

        entries.forEach {
            Text("${it.text} (${it.date})")
        }
    }
}