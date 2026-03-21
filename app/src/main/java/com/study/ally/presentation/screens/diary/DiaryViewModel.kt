package com.study.ally.presentation.screens.diary

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDateTime

class DiaryViewModel : ViewModel() {

    private val _entries = MutableStateFlow<List<DiaryEntry>>(emptyList())
    val entries: StateFlow<List<DiaryEntry>> = _entries

    fun addEntry(text: String) {
        val newEntry = DiaryEntry(
            id = _entries.value.size + 1,
            text = text,
            date = LocalDateTime.now()
        )
        _entries.value += newEntry
    }
}