package com.study.ally

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.study.ally.data.datastore.DataStoreManager
import com.study.ally.domain.model.DiaryEntry
import com.study.ally.domain.model.Medicine
import com.study.ally.domain.model.PassportData
import com.study.ally.domain.model.Symptom
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DataStoreManagerTest {

    private lateinit var context: Context
    private lateinit var dataStoreManager: DataStoreManager

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        dataStoreManager = DataStoreManager(context)
        runBlocking {
            dataStoreManager.savePassport(PassportData(emptyList(), emptyList(), "", ""))
            dataStoreManager.saveDiary(emptyList())
            dataStoreManager.saveSymptoms(emptyList())
        }
    }

    @After
    fun tearDown() {
        // Очищаем хранилище после теста
        runBlocking {
            dataStoreManager.savePassport(PassportData(emptyList(), emptyList(), "", ""))
            dataStoreManager.saveDiary(emptyList())
            dataStoreManager.saveSymptoms(emptyList())
        }
    }

    @Test
    fun passportFlow_returnsDefaultWhenEmpty() {
        runBlocking {
            val passport = dataStoreManager.passportFlow.first()
            assertEquals(emptyList<String>(), passport.allergens)
            assertEquals(emptyList<Medicine>(), passport.medicines)
            assertEquals("", passport.doctor)
            assertEquals("", passport.contact)
        }
    }

    @Test
    fun savePassport_updatesFlow() {
        runBlocking {
            val data = PassportData(
                allergens = listOf("Pollen"),
                medicines = listOf(Medicine("Antihistamine", "10mg")),
                doctor = "Dr. House",
                contact = "123456"
            )
            dataStoreManager.savePassport(data)

            val loaded = dataStoreManager.passportFlow.first()
            assertEquals(data.allergens, loaded.allergens)
            assertEquals(data.medicines, loaded.medicines)
            assertEquals(data.doctor, loaded.doctor)
            assertEquals(data.contact, loaded.contact)
        }
    }

    @Test
    fun diaryFlow_returnsEmptyListWhenNoData() {
        runBlocking {
            val entries = dataStoreManager.diaryFlow.first()
            assertTrue(entries.isEmpty())
        }
    }

    @Test
    fun saveDiary_updatesFlow() {
        runBlocking {
            val entry = DiaryEntry(1, "Test", System.currentTimeMillis())
            dataStoreManager.saveDiary(listOf(entry))

            val loaded = dataStoreManager.diaryFlow.first()
            assertEquals(1, loaded.size)
            assertEquals(entry.text, loaded[0].text)
        }
    }

    @Test
    fun symptomsFlow_returnsEmptyListWhenNoData() {
        runBlocking {
            val symptoms = dataStoreManager.symptomsFlow.first()
            assertTrue(symptoms.isEmpty())
        }
    }

    @Test
    fun saveSymptoms_updatesFlow() {
        runBlocking {
            val symptom = Symptom("Sneeze", 2f, "Morning", System.currentTimeMillis())
            dataStoreManager.saveSymptoms(listOf(symptom))

            val loaded = dataStoreManager.symptomsFlow.first()
            assertEquals(1, loaded.size)
            assertEquals(symptom.name, loaded[0].name)
        }
    }
}