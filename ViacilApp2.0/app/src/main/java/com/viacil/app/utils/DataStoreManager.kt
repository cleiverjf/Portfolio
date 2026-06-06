package com.viacil.app.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "viacil_settings")

class DataStoreManager(private val context: Context) {

    private val highContrastKey = booleanPreferencesKey("high_contrast")
    private val daltonismModeKey = intPreferencesKey("daltonism_mode")
    private val textSizeKey = intPreferencesKey("text_size")

    val highContrast: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[highContrastKey] ?: false }

    val daltonismMode: Flow<Int> = context.dataStore.data
        .map { preferences -> preferences[daltonismModeKey] ?: 0 }

    val textSize: Flow<Int> = context.dataStore.data
        .map { preferences -> preferences[textSizeKey] ?: 1 }

    suspend fun setHighContrast(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[highContrastKey] = enabled
        }
    }

    suspend fun setDaltonismMode(mode: Int) {
        context.dataStore.edit { preferences ->
            preferences[daltonismModeKey] = mode
        }
    }

    suspend fun setTextSize(size: Int) {
        context.dataStore.edit { preferences ->
            preferences[textSizeKey] = size
        }
    }
}