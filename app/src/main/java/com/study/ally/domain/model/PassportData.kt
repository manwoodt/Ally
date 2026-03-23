package com.study.ally.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PassportData(
    val allergens: List<String>,
    val medicines: List<Medicine>,
    val doctor: String,
    val contact: String,
)