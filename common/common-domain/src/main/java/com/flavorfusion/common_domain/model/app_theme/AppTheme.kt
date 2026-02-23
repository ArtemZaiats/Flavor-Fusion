package com.flavorfusion.common_domain.model.app_theme

data class AppTheme(
    val title: String,
    val type: ThemeType
)

enum class ThemeType {
    LIGHT,
    DARK,
    SYSTEM
}