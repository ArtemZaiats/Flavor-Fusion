package com.flavorfusion.common_data.local_storage.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.flavorfusion.common_domain.model.UserProfile
import com.flavorfusion.common_domain.model.app_theme.AppTheme
import com.flavorfusion.common_domain.model.app_theme.ThemeType
import com.flavorfusion.common_data.local_storage.model.AppThemeEntity
import com.flavorfusion.common_data.local_storage.model.UserProfileEntity
import com.flavorfusion.common_data.local_storage.model.toDomain
import com.flavorfusion.common_data.local_storage.model.toEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

private const val DATA_STORE_NAME = "settings_data_store"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_NAME)

@Singleton
class DataStoreHelper @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    private val themeKey = stringPreferencesKey("theme")
    private val alcoholicKey = booleanPreferencesKey("show_alcoholic")
    private val userProfileKey = stringPreferencesKey("user_profile")

    val appThemeFlow: Flow<AppTheme> = context.dataStore.data.map { preferences ->
        val json = preferences[themeKey]
        if (json != null) {
            try {
                Json.decodeFromString<AppThemeEntity>(json).toDomain()
            } catch (e: Exception) {
                AppTheme("System", ThemeType.SYSTEM)
            }
        } else {
            AppTheme("System", ThemeType.SYSTEM)
        }
    }

    suspend fun updateTheme(appTheme: AppTheme) {
        val json = Json.encodeToString(appTheme.toEntity())
        saveToStore(themeKey, json)
    }

    val showAlcoholicFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[alcoholicKey] ?: true
    }

    suspend fun setShowAlcoholic(showAlcoholic: Boolean) {
        saveToStore(alcoholicKey, showAlcoholic)
    }

    val userProfileFlow: Flow<UserProfile?> = context.dataStore.data.map { preferences ->
        val json = preferences[userProfileKey] ?: return@map null
        try {
            Json.decodeFromString<UserProfileEntity>(json).toDomain()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun saveUserProfile(profile: UserProfile) {
        val json = Json.encodeToString(profile.toEntity())
        saveToStore(userProfileKey, json)
    }

    suspend fun clearAllData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    private suspend fun <T> saveToStore(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}