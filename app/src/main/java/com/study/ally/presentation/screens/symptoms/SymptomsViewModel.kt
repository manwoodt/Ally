package com.study.ally.presentation.screens.symptoms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.ally.data.datastore.DataStoreManager
import com.study.ally.domain.model.Symptom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SymptomsViewModel(
    private val dataStore: DataStoreManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SymptomsUiState())
    val uiState: StateFlow<SymptomsUiState> = _uiState

    init {
        observe()
    }

    private fun observe() {
        viewModelScope.launch {
            dataStore.symptomsFlow.collect { list ->
                _uiState.update {
                    it.copy(symptoms = list)
                }
            }
        }
    }

    fun onLongClick(name: String) {
        _uiState.update {
            it.copy(
                showSlider = true,
                selected = name
            )
        }
    }

    fun onIntensityChange(value: Float) {
        _uiState.update {
            it.copy(intensity = value)
        }
    }

    fun save() {

        val state = _uiState.value
        val name = state.selected ?: return

        val time = when (System.currentTimeMillis() % 3) {
            0L -> "Утро"
            1L -> "День"
            else -> "Вечер"
        }

        val new = Symptom(
            name = name,
            intensity = state.intensity,
            timeOfDay = time,
            timestamp = System.currentTimeMillis()
        )

        val updated = state.symptoms + new

        viewModelScope.launch {
            dataStore.saveSymptoms(updated)
        }

        _uiState.update {
            it.copy(showSlider = false)
        }
    }
}