package com.study.ally.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Symptom(
    val name: String,
    val intensity: Float,
    val timeOfDay: String,
    val timestamp: Long,
)
