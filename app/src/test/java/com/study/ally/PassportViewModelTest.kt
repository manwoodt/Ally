package com.study.ally

import com.study.ally.data.datastore.DataStoreManager
import com.study.ally.domain.model.Medicine
import com.study.ally.domain.model.PassportData
import com.study.ally.presentation.screens.passport.PassportViewModel
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
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PassportViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var dataStore: DataStoreManager

    private lateinit var viewModel: PassportViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        val passportFlow = MutableStateFlow(
            PassportData(
                allergens = listOf("Pollen"),
                medicines = listOf(Medicine("Loratadine", "10mg")),
                doctor = "Dr. House",
                contact = "123456"
            )
        )
        whenever(dataStore.passportFlow).thenReturn(passportFlow)

        viewModel = PassportViewModel(dataStore)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun init_loadsDataFromDataStore() {
        runTest {
            val state = viewModel.uiState.value
            assertEquals("Pollen", state.data.allergens.first())
            assertEquals("Loratadine", state.data.medicines.first().name)
            assertEquals("Dr. House", state.data.doctor)
            assertEquals("123456", state.data.contact)
        }
    }

    @Test
    fun toggleEdit_changesEditingState() {
        assertFalse(viewModel.uiState.value.isEditing)
        viewModel.toggleEdit()
        assertTrue(viewModel.uiState.value.isEditing)
        viewModel.toggleEdit()
        assertFalse(viewModel.uiState.value.isEditing)
    }

    @Test
    fun updateAllergens_savesNewList() {
        runTest {
            val newAllergens = listOf("Dust", "Pollen")
            viewModel.updateAllergens(newAllergens)

            advanceUntilIdle()
            verify(dataStore).savePassport(
                PassportData(
                    allergens = newAllergens,
                    medicines = viewModel.uiState.value.data.medicines,
                    doctor = viewModel.uiState.value.data.doctor,
                    contact = viewModel.uiState.value.data.contact
                )
            )
            assertEquals(newAllergens, viewModel.uiState.value.data.allergens)
        }
    }

    @Test
    fun updateMeds_savesNewList() {
        runTest {
            val newMeds = listOf(Medicine("Cetirizine", "5mg"))
            viewModel.updateMeds(newMeds)

            advanceUntilIdle()
            verify(dataStore).savePassport(
                PassportData(
                    allergens = viewModel.uiState.value.data.allergens,
                    medicines = newMeds,
                    doctor = viewModel.uiState.value.data.doctor,
                    contact = viewModel.uiState.value.data.contact
                )
            )
            assertEquals(newMeds, viewModel.uiState.value.data.medicines)
        }
    }

    @Test
    fun updateDoctor_savesNewValue() {
        runTest {
            viewModel.updateDoctor("Dr. Wilson")
            advanceUntilIdle()
            verify(dataStore).savePassport(
                viewModel.uiState.value.data.copy(doctor = "Dr. Wilson")
            )
            assertEquals("Dr. Wilson", viewModel.uiState.value.data.doctor)
        }
    }

    @Test
    fun updateContact_savesNewValue() {
        runTest {
            viewModel.updateContact("987654")
            advanceUntilIdle()
            verify(dataStore).savePassport(
                viewModel.uiState.value.data.copy(contact = "987654")
            )
            assertEquals("987654", viewModel.uiState.value.data.contact)
        }
    }
}