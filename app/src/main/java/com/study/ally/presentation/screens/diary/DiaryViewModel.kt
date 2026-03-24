package com.study.ally.presentation.screens.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.ally.data.datastore.DataStoreManager
import com.study.ally.domain.model.DiaryEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DiaryViewModel(
    private val dataStore: DataStoreManager,
) : ViewModel() {

    private val _eventsInput = MutableStateFlow("")
    val eventsInput: StateFlow<String> = _eventsInput

    private val _medicationsInput = MutableStateFlow("")
    val medicationsInput: StateFlow<String> = _medicationsInput

    val entries = dataStore.diaryFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun onEventsChange(value: String) {
        _eventsInput.value = value
    }

    fun onMedicationsChange(value: String) {
        _medicationsInput.value = value
    }

    fun add() {
        val events = _eventsInput.value.trim()
        val medications = _medicationsInput.value.trim()

        val combined = buildString {
            if (events.isNotBlank()) append(events)
            if (events.isNotBlank() && medications.isNotBlank()) append("; ")
            if (medications.isNotBlank()) append(medications)
        }

        if (combined.isEmpty()) return

        val newEntry = DiaryEntry(
            id = System.currentTimeMillis().toInt(),
            text = combined,
            timestamp = System.currentTimeMillis()
        )

        val updated = entries.value + newEntry

        viewModelScope.launch {
            dataStore.saveDiary(updated)
        }

    }
}