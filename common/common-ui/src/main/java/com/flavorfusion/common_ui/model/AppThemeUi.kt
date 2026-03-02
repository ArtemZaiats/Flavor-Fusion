package com.flavorfusion.common_ui.model

import com.flavorfusion.common_domain.model.app_theme.AppTheme
import com.flavorfusion.common_domain.model.app_theme.ThemeType

data class AppThemeUi(
    val isSelected: Boolean = true,
    val theme: AppTheme = AppTheme(
        title = "",
        type = ThemeType.SYSTEM
    )
)

fun AppTheme.toUi() = AppThemeUi(
    isSelected = false,
    theme = this,
)