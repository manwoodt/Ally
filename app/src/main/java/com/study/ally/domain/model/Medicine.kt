package com.study.ally.domain.model

import kotlinx.serialization.Serializable

@Serializable

data class Medicine(
    val name: String,
    val dosage: String
)
