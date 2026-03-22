package com.study.ally.presentation.screens.passport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun PassportScreen(
    viewModel: PassportViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()

    var localState by remember { mutableStateOf(state) }

    LaunchedEffect(state) {
        localState = state
    }

    Column(
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        OutlinedTextField(
            value = localState.allergens,
            onValueChange = {
                localState = localState.copy(allergens = it)
            },
            label = { Text("Аллергены (опасно)") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = localState.meds,
            onValueChange = {
                localState = localState.copy(meds = it)
            },
            label = { Text("Препараты") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = localState.doctor,
            onValueChange = {
                localState = localState.copy(doctor = it)
            },
            label = { Text("Врач") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = localState.contact,
            onValueChange = {
                localState = localState.copy(contact = it)
            },
            label = { Text("Контакт") }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.save(localState) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сохранить")
        }
    }
}