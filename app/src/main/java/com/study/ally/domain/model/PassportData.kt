package com.study.ally.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PassportData(
    val allergens: String,
    val meds: String,
    val doctor: String,
    val contact: String
)