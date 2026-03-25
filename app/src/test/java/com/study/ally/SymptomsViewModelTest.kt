package com.study.ally

import com.study.ally.data.datastore.DataStoreManager
import com.study.ally.domain.model.Symptom
import com.study.ally.presentation.screens.symptoms.SymptomsViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class SymptomsViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var dataStore: DataStoreManager

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun onLongClick_showsSliderAndSelectsSymptom() = runTest {
        val symptomsFlow = MutableStateFlow(emptyList<Symptom>())
        whenever(dataStore.symptomsFlow).thenReturn(symptomsFlow)

        val viewModel = SymptomsViewModel(dataStore)
        advanceUntilIdle()

        viewModel.onLongClick("Sneeze")

        val state = viewModel.uiState.value
        assertTrue(state.showSlider)
        assertEquals("Sneeze", state.selected)
    }

    @Test
    fun onIntensityChange_updatesIntensity() = runTest {
        val symptomsFlow = MutableStateFlow(emptyList<Symptom>())
        whenever(dataStore.symptomsFlow).thenReturn(symptomsFlow)

        val viewModel = SymptomsViewModel(dataStore)
        advanceUntilIdle()

        viewModel.onIntensityChange(2.5f)
        assertEquals(2.5f, viewModel.uiState.value.intensity, 0.01f)
    }

    @Test
    fun save_createsSymptomAndSaves() = runTest {
        val symptomsFlow = MutableStateFlow(emptyList<Symptom>())
        whenever(dataStore.symptomsFlow).thenReturn(symptomsFlow)

        val viewModel = SymptomsViewModel(dataStore)
        advanceUntilIdle()

        viewModel.onLongClick("Sneeze")
        viewModel.onIntensityChange(2f)

        viewModel.save()

        advanceUntilIdle()
        argumentCaptor<List<Symptom>>().apply {
            verify(dataStore).saveSymptoms(capture())
            val saved = firstValue
            assertEquals(1, saved.size)
            assertEquals("Sneeze", saved[0].name)
            assertEquals(2f, saved[0].intensity, 0.01f)
            assertTrue(saved[0].timeOfDay.isNotEmpty())
            assertTrue(saved[0].timestamp > 0)
        }

        assertFalse(viewModel.uiState.value.showSlider)
    }

    @Test
    fun save_whenNoSelected_doesNothing() = runTest {
        val symptomsFlow = MutableStateFlow(emptyList<Symptom>())
        whenever(dataStore.symptomsFlow).thenReturn(symptomsFlow)

        val viewModel = SymptomsViewModel(dataStore)
        advanceUntilIdle()

        viewModel.save()

        advanceUntilIdle()
        verify(dataStore, never()).saveSymptoms(any<List<Symptom>>())
        assertFalse(viewModel.uiState.value.showSlider)
    }

    @Test
    fun init_loadsSymptomsFromDataStore() = runTest {
        val existingSymptoms = listOf(
            Symptom("Sneeze", 1f, "Morning", System.currentTimeMillis())
        )
        val symptomsFlow = MutableStateFlow(existingSymptoms)
        whenever(dataStore.symptomsFlow).thenReturn(symptomsFlow)

        val viewModel = SymptomsViewModel(dataStore)
        advanceUntilIdle()

        assertEquals(existingSymptoms, viewModel.uiState.value.symptoms)
    }
}