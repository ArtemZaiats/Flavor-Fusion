package com.flavorfusion.common_domain.repositories

import com.flavorfusion.common_domain.model.app_theme.AppTheme
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getCurrentAppThemeFlow(): Flow<AppTheme?>
    suspend fun setAppTheme(appTheme: AppTheme)

    suspend fun clearData()
}