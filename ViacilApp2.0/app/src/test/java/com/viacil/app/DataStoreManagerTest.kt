package com.viacil.app

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.viacil.app.utils.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DataStoreManagerTest {

    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        dataStoreManager = DataStoreManager(context)
    }

    @Test
    fun testHighContrastDefaultValue() = runBlocking {
        val defaultValue = dataStoreManager.highContrast.first()
        assertEquals(false, defaultValue)
    }

    @Test
    fun testSetAndGetHighContrast() = runBlocking {
        dataStoreManager.setHighContrast(true)
        val value = dataStoreManager.highContrast.first()
        assertEquals(true, value)
    }
}