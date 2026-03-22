package com.study.ally.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DiaryEntry(
    val id: Int,
    val text: String,
    val timestamp: Long
)