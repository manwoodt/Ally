package com.study.ally.presentation.screens.symptoms

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SymptomsViewModel : ViewModel() {

    private val _symptoms = MutableStateFlow<List<Symptom>>(emptyList())
    val symptoms: StateFlow<List<Symptom>> = _symptoms

    fun addSymptom(name: String, intensity: Int) {

        val time = when (System.currentTimeMillis() % 3) {
            0L -> "Утро"
            1L -> "День"
            else -> "Вечер"
        }

        val new = Symptom(name, intensity, time)

        _symptoms.value += new
    }
}