package com.loyds.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.loyds.data.model.Theme
import com.loyds.data.model.UserPreferences
import com.loyds.data.preferences.PreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            emit(emptyPreferences())
        }.map { preferences ->
            val theme = Theme.valueOf(preferences[PreferencesKeys.THEME] ?: "SYSTEM")
            val dynamic = preferences[PreferencesKeys.DYNAMIC] ?: false
            UserPreferences(
                theme = theme,
                dynamicColor = dynamic,
            )
        }

    suspend fun updateTheme(theme: Theme) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME] = theme.name
        }
    }

    suspend fun updateDynamicColors(dynamic: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.DYNAMIC] = dynamic
        }
    }
}