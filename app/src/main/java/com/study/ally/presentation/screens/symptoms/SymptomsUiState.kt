package com.study.ally.presentation.screens.symptoms

import com.study.ally.domain.model.Symptom

data class SymptomsUiState(
    val showSlider: Boolean = false,
    val intensity: Float = 1f,
    val selected: String? = null,
    val symptoms: List<Symptom> = emptyList()
)
