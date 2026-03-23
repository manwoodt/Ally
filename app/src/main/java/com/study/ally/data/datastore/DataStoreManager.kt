package com.study.ally.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.study.ally.domain.model.DiaryEntry
import com.study.ally.domain.model.PassportData
import com.study.ally.domain.model.Symptom
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json


private val Context.dataStore by preferencesDataStore("ally_prefs")

class DataStoreManager(
    private val context: Context,
) {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val PASSPORT = stringPreferencesKey("passport")
    private val DIARY = stringPreferencesKey("diary")
    private val SYMPTOMS = stringPreferencesKey("symptoms")

    val passportFlow = context.dataStore.data.map { prefs ->
        val raw = prefs[PASSPORT]
        if (raw == null) {
            PassportData(emptyList(), emptyList(), "", "")
        } else {
            json.decodeFromString<PassportData>(raw)
        }
    }

    suspend fun savePassport(data: PassportData) {
        context.dataStore.edit {
            it[PASSPORT] = json.encodeToString(data)
        }
    }


    val diaryFlow = context.dataStore.data.map { prefs ->
        val raw = prefs[DIARY]
        if (raw == null) {
            emptyList()
        } else {
            json.decodeFromString<List<DiaryEntry>>(raw)
        }
    }

    suspend fun saveDiary(list: List<DiaryEntry>) {
        context.dataStore.edit {
            it[DIARY] = json.encodeToString(list)
        }
    }


    val symptomsFlow = context.dataStore.data.map { prefs ->
        val raw = prefs[SYMPTOMS]
        if (raw == null) {
            emptyList()
        } else {
            json.decodeFromString<List<Symptom>>(raw)
        }
    }

    suspend fun saveSymptoms(list: List<Symptom>) {
        context.dataStore.edit {
            it[SYMPTOMS] = json.encodeToString(list)
        }
    }
}