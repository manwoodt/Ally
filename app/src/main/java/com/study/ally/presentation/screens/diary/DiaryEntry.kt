package com.study.ally.presentation.screens.diary

import java.time.LocalDateTime

data class DiaryEntry(
    val id: Int,
    val text: String,
    val date: LocalDateTime
)
