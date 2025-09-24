package com.fahimdev.core.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {
    private companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("DATA_STORE")
    }

    suspend fun saveString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[prefKey] = value
        }
    }

    suspend fun getString(key: String): String? {
        val prefKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[prefKey]
    }

    suspend fun saveInt(key: String, value: Int) {
        val prefKey = intPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[prefKey] = value
        }
    }

    suspend fun getInt(key: String): Int? {
        val prefKey = intPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[prefKey]
    }

    suspend fun saveBoolean(key: String, value: Boolean) {
        val prefKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[prefKey] = value.toString()
        }
    }

    suspend fun getBoolean(key: String): Boolean? {
        val prefKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[prefKey]?.toBoolean()
    }

    suspend fun saveList(key: String, value: List<String>) {
        val prefKey = stringSetPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[prefKey] = value.toSet()
        }
    }

    suspend fun getList(key: String, defaultValue: List<String> = emptyList()): List<String> {
        val prefKey = stringSetPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[prefKey]?.toList() ?: defaultValue
    }

    suspend fun clearAll() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun clear(key: String) {
        val prefKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences.remove(prefKey)
        }
    }

}