package com.flavorfusion.settings.providers

import com.flavorfusion.common_domain.model.app_theme.AppTheme
import com.flavorfusion.common_domain.model.app_theme.ThemeType
import com.flavorfusion.common_ui.model.AppThemeUi
import com.flavorfusion.common_ui.utils.DataProvider

class AppThemesDataProvider : DataProvider<List<AppThemeUi>> {
    override fun provideData(): List<AppThemeUi> {
        return listOf(
            AppThemeUi(
                theme = AppTheme(
                    title = "Light",
                    type = ThemeType.LIGHT
                ),
                isSelected = false
            ),
            AppThemeUi(
                theme = AppTheme(
                    title = "Dark",
                    type = ThemeType.DARK
                ),
                isSelected = false
            ),
            AppThemeUi(
                theme = AppTheme(
                    title = "System",
                    type = ThemeType.SYSTEM
                ),
                isSelected = false
            ),
        )
    }
}