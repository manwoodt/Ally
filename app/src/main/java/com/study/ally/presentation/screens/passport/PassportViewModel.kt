package com.study.ally.presentation.screens.passport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.ally.domain.model.Allergen
import com.study.ally.domain.usecase.GetAllergensUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PassportViewModel(
    private val getAllergensUseCase: GetAllergensUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<List<Allergen>>(emptyList())
    val state: StateFlow<List<Allergen>> = _state

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            _state.value = getAllergensUseCase()
        }
    }
}