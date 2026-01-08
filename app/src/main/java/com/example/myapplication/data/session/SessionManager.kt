package com.example.myapplication.data.session

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SessionManager(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val SESSION_ID = stringPreferencesKey("session_id")
    }

    suspend fun saveSessionId(sessionId: String) {
        dataStore.edit { prefs ->
            prefs[SESSION_ID] = sessionId
        }
    }

    fun getSessionId(): Flow<String?> =
        dataStore.data.map { prefs -> prefs[SESSION_ID] }
}
