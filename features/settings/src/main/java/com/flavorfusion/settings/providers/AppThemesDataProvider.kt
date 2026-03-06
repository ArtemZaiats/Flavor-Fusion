package com.flavorfusion.settings.providers

import android.content.Context
import com.flavorfusion.common_domain.model.app_theme.AppTheme
import com.flavorfusion.common_domain.model.app_theme.ThemeType
import com.flavorfusion.common_ui.R
import com.flavorfusion.common_ui.model.AppThemeUi
import com.flavorfusion.common_ui.utils.DataProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppThemesDataProvider @Inject constructor(
    @param:ApplicationContext private val context: Context
) : DataProvider<List<AppThemeUi>> {
    override fun provideData(): List<AppThemeUi> {
        return listOf(
            AppThemeUi(
                theme = AppTheme(
                    title = context.getString(R.string.feature_settings_app_theme_light),
                    type = ThemeType.LIGHT
                ),
                isSelected = false
            ),
            AppThemeUi(
                theme = AppTheme(
                    title = context.getString(R.string.feature_settings_app_theme_dark),
                    type = ThemeType.DARK
                ),
                isSelected = false
            ),
            AppThemeUi(
                theme = AppTheme(
                    title = context.getString(R.string.feature_settings_app_theme_system),
                    type = ThemeType.SYSTEM
                ),
                isSelected = false
            ),
        )
    }
}