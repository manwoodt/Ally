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
import java.time.LocalDateTime

// заменить ли System.currentTimeMillis() на LocalDateTime

class DiaryViewModel(
    private val dataStore: DataStoreManager
) : ViewModel() {

    private val _input = MutableStateFlow("")
    val input: StateFlow<String> = _input

    val entries = dataStore.diaryFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun onTextChange(value: String) {
        _input.value = value
    }

    fun add() {

        val text = _input.value.trim()

        if (text.isEmpty()) return

        val new = DiaryEntry(
            id = System.currentTimeMillis().toInt(),
            text = text,
            timestamp = System.currentTimeMillis()
        )

        val updated = entries.value + new

        viewModelScope.launch {
            dataStore.saveDiary(updated)
        }

        _input.value = ""
    }
}