package com.flavorfusion.common_data.repositories

import com.flavorfusion.common_domain.model.app_theme.AppTheme
import com.flavorfusion.common_domain.repositories.SettingsRepository
import com.flavorfusion.common_data.local_storage.shared_preferences.DataStoreHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataStoreHelper: DataStoreHelper
) : SettingsRepository {

    override fun getCurrentAppThemeFlow(): Flow<AppTheme?> = dataStoreHelper.appThemeFlow
    override fun getIsShowAlcoholicFlow(): Flow<Boolean> = dataStoreHelper.showAlcoholicFlow

    override suspend fun setAppTheme(appTheme: AppTheme) =
        dataStoreHelper.updateTheme(appTheme)

    override suspend fun setShowAlcoholic(showAlcoholic: Boolean) =
        dataStoreHelper.setShowAlcoholic(showAlcoholic)

    override suspend fun clearData() = dataStoreHelper.clearAllData()
}