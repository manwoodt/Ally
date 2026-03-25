package com.study.ally
import com.study.ally.data.datastore.DataStoreManager
import com.study.ally.domain.model.DiaryEntry
import com.study.ally.presentation.screens.diary.DiaryViewModel
import junit.framework.TestCase.assertEquals
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
class DiaryViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var dataStore: DataStoreManager

    private lateinit var viewModel: DiaryViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        val diaryFlow = MutableStateFlow(emptyList<DiaryEntry>())
        whenever(dataStore.diaryFlow).thenReturn(diaryFlow)

        viewModel = DiaryViewModel(dataStore)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun onEventsChange_updatesEventsInput() {
        viewModel.onEventsChange("Test event")
        assertEquals("Test event", viewModel.eventsInput.value)
    }

    @Test
    fun onMedicationsChange_updatesMedicationsInput() {
        viewModel.onMedicationsChange("Med A")
        assertEquals("Med A", viewModel.medicationsInput.value)
    }

    @Test
    fun add_withBothFields_createsEntryAndSaves() {
        runTest {
            viewModel.onEventsChange("Sneeze")
            viewModel.onMedicationsChange("Antihistamine")
            viewModel.add()

            advanceUntilIdle()
            argumentCaptor<List<DiaryEntry>>().apply {
                verify(dataStore).saveDiary(capture())
                val saved = firstValue
                assertEquals(1, saved.size)
                assertTrue(saved[0].text.contains("Sneeze") && saved[0].text.contains("Antihistamine"))
                assertTrue(saved[0].id > 0)
                assertTrue(saved[0].timestamp > 0)
            }
        }
    }

    @Test
    fun add_withOnlyEvents_createsEntry() {
        runTest {
            viewModel.onEventsChange("Only event")
            viewModel.onMedicationsChange("")
            viewModel.add()

            advanceUntilIdle()
            argumentCaptor<List<DiaryEntry>>().apply {
                verify(dataStore).saveDiary(capture())
                assertEquals("Only event", firstValue[0].text)
            }
        }
    }

    @Test
    fun add_withEmptyFields_doesNotSave() {
        runTest {
            viewModel.onEventsChange("")
            viewModel.onMedicationsChange("")
            viewModel.add()

            advanceUntilIdle()
            verify(dataStore, never()).saveDiary(any<List<DiaryEntry>>())
        }
    }
}