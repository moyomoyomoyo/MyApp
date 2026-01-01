package com.example.myapplication.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first

class SettingsRepository(private val dataStore: DataStore<Preferences>){
    private companion object {
        val FIRST_LAUNCH = booleanPreferencesKey("first_launch")
    }

    suspend fun isFirstLaunch(): Boolean {
        val prefs = dataStore.data.first()
        return prefs[FIRST_LAUNCH] ?: true
    }

    suspend fun setFirstLaunchDone() {
        dataStore.edit { prefs ->
            prefs[FIRST_LAUNCH] = false
        }
    }
}