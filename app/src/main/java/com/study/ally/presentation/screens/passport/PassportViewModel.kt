package com.study.ally.presentation.screens.passport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.ally.data.datastore.DataStoreManager
import com.study.ally.domain.model.Medicine
import com.study.ally.domain.model.PassportData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PassportViewModel(
    private val dataStore: DataStoreManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PassportUiState())
    val uiState: StateFlow<PassportUiState> = _uiState

    init {
        viewModelScope.launch {
            dataStore.passportFlow.collect { data ->
                _uiState.value = _uiState.value.copy(data = data)
            }
        }
    }

    fun toggleEdit() {
        _uiState.value = _uiState.value.copy(
            isEditing = !_uiState.value.isEditing
        )
    }

    fun updateAllergens(list: List<String>) {
        updateData(_uiState.value.data.copy(allergens = list))
    }

    fun updateMeds(list: List<Medicine>) {
        updateData(_uiState.value.data.copy(medicines = list))
    }

    fun updateDoctor(value: String) {
        updateData(_uiState.value.data.copy(doctor = value))
    }

    fun updateContact(value: String) {
        updateData(_uiState.value.data.copy(contact = value))
    }

    private fun updateData(newData: PassportData) {

        _uiState.value = _uiState.value.copy(data = newData)

        viewModelScope.launch {
            dataStore.savePassport(newData)
        }
    }
}