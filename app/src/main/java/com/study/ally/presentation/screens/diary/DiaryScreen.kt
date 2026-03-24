package com.study.ally.presentation.screens.diary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel


@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel = koinViewModel(),
) {

    val entries by viewModel.entries.collectAsState()
    val events by viewModel.eventsInput.collectAsState()
    val medications by viewModel.medicationsInput.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        DiaryInputBlock(
            eventsText = events,
            onEventsTextChange = { value ->
                viewModel.onEventsChange(value)
            },
            medicationsText = medications,
            onMedicationsTextChange = { value ->
                viewModel.onMedicationsChange(value)
            },
            onAddClick = { viewModel.add() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        DiaryListBlock(entries = entries)
    }
}