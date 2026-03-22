package com.study.ally.presentation.screens.passport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.ally.data.datastore.DataStoreManager
import com.study.ally.domain.model.PassportData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PassportViewModel(
    private val dataStore: DataStoreManager
) : ViewModel() {

    val state = dataStore.passportFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        PassportData("", "", "", "")
    )

    fun save(data: PassportData) {
        viewModelScope.launch {
            dataStore.savePassport(data)
        }
    }
}