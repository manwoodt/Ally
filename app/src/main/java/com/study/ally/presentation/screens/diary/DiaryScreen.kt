package com.study.ally.presentation.screens.diary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.study.ally.presentation.utils.toReadableTime
import kotlinx.coroutines.flow.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel = koinViewModel(),
) {

    val entries by viewModel.entries.collectAsState()
    val text by viewModel.input.collectAsState()

    Column(Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = text,
            onValueChange = { value ->
                viewModel.onTextChange(value)
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Что съел / триггер") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = viewModel::add,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Добавить")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(entries) { entry ->
                Text("${entry.text} (${entry.timestamp.toReadableTime()})")
            }
        }
    }
}