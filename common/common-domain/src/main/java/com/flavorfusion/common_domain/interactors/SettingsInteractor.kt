package com.flavorfusion.common_domain.interactors

import com.flavorfusion.common_domain.model.app_theme.AppTheme
import com.flavorfusion.common_domain.repositories.SettingsRepository
import javax.inject.Inject

class SettingsInteractor @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    fun getCurrentAppThemeFlow() = settingsRepository.getCurrentAppThemeFlow()
    fun getShowAlcoholicFlow() = settingsRepository.getIsShowAlcoholicFlow()
    suspend fun setCurrentAppTheme(appTeme: AppTheme) = settingsRepository.setAppTheme(appTeme)
    suspend fun setShowAlcoholic(showAlcoholic: Boolean) = settingsRepository.setShowAlcoholic(showAlcoholic)

    suspend fun clearData() = settingsRepository.clearData()
}