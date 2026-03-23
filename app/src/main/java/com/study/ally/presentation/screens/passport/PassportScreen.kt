package com.study.ally.presentation.screens.passport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun PassportScreen(
    viewModel: PassportViewModel = koinViewModel(),
) {

    val uiState by viewModel.uiState.collectAsState()

    val data = uiState.data
    val isEditing = uiState.isEditing

    Column(
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Header(
            isEditing = isEditing,
            onToggle = { viewModel.toggleEdit() }
        )

        AllergensBlock(
            allergens = data.allergens,
            isEditing = isEditing,
            onAdd = { new ->
                viewModel.updateAllergens(data.allergens + new)
            }
        )

        Spacer(Modifier.height(12.dp))

        MedicinesBlock(
            medicines = data.medicines,
            isEditing = isEditing,
            onAdd = { med ->
                viewModel.updateMeds(data.medicines + med)
            }
        )

        Spacer(Modifier.height(12.dp))

        ContactsBlock(
            doctor = data.doctor,
            contact = data.contact,
            isEditing = isEditing,
            onDoctorChange = { value ->
                viewModel.updateDoctor(value)
            },
            onContactChange = { value ->
                viewModel.updateContact(value)
            }
        )

        Spacer(Modifier.height(24.dp))
    }
}