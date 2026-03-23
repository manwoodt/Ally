package com.study.ally.presentation.screens.passport

import com.study.ally.domain.model.PassportData

data class PassportUiState(
    val data: PassportData = PassportData(
        allergens = emptyList(),
        medicines = emptyList(),
        doctor = "",
        contact = ""
    ),
    val isEditing: Boolean = false
)


