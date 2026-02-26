package com.flavorfusion.settings.model

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsCategory(
    val id: Int,
    val title: String,
    val items: List<MenuItem>
)

data class MenuItem(
    val id: Int,
    val isActive: Boolean = true,
    val title: String,
    val icon: ImageVector,
    val category: Category,
    val showSwitch: Boolean = false,
    val isSwitchActive: Boolean = false,
    val onSwitchClicked: () -> Unit = {}
)

enum class Category(val id: Int, val title: String) {
    APP(1, "App"),
    CONTENT_PREFERENCES(2, "Content preferences"),
    ABOUT(3, "About"),
}

enum class CategoryItem(val id: Int) {
    LANGUAGE(1),
    APP_THEME(2),
    SHOW_ALCOHOLIC(3),
    PRIVACY_POLICY(4),
    TERMS_OF_USE(5),
}