package com.flavorfusion.common_data.local_storage.shared_preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.flavorfusion.common_domain.model.app_theme.AppTheme
import com.flavorfusion.common_domain.model.app_theme.ThemeType
import com.flavorfusion.common_data.local_storage.model.AppThemeEntity
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
        context.dataStore.edit { preferences ->
            preferences[themeKey] = json
        }
    }

    suspend fun clearAllData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}