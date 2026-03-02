package com.flavorfusion.settings.providers

import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsAlcoholic
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsAppTheme
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsLanguage
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsPrivacyPolicy
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsTermsOfUse
import com.flavorfusion.common_ui.utils.DataProvider
import com.flavorfusion.settings.model.Category
import com.flavorfusion.settings.model.CategoryItem
import com.flavorfusion.settings.model.MenuItem
import com.flavorfusion.settings.model.SettingsCategory

class SettingsCategoryProvider : DataProvider<List<SettingsCategory>> {
    override fun provideData(): List<SettingsCategory> {
        val appCategoriesItems = listOf(
            MenuItem(
                id = CategoryItem.LANGUAGE.id,
                isActive = false,
                title = "Language",
                icon = AppIcons.SettingsLanguage,
                category = Category.APP,
            ),
            MenuItem(
                id = CategoryItem.APP_THEME.id,
                title = "App theme",
                icon = AppIcons.SettingsAppTheme,
                category = Category.APP,
            ),
        )

        val contentPreferencesItems = listOf(
            MenuItem(
                id = CategoryItem.SHOW_ALCOHOLIC.id,
                title = "Show alcoholic drinks",
                icon = AppIcons.SettingsAlcoholic,
                category = Category.CONTENT_PREFERENCES,
                showSwitch = true,
                isSwitchActive = true
            ),
        )

        val aboutItems = listOf(
            MenuItem(
                id = CategoryItem.PRIVACY_POLICY.id,
                title = "Privacy policy",
                icon = AppIcons.SettingsPrivacyPolicy,
                category = Category.ABOUT,
            ),
            MenuItem(
                id = CategoryItem.TERMS_OF_USE.id,
                title = "Terms of use",
                icon = AppIcons.SettingsTermsOfUse,
                category = Category.ABOUT,
            ),
        )

        val appCategory = SettingsCategory(
            id = Category.APP.id,
            title = Category.APP.title,
            items = appCategoriesItems
        )

        val contentPreferencesCategory = SettingsCategory(
            id = Category.CONTENT_PREFERENCES.id,
            title = Category.CONTENT_PREFERENCES.title,
            items = contentPreferencesItems
        )

        val aboutCategory = SettingsCategory(
            id = Category.ABOUT.id,
            title = Category.ABOUT.title,
            items = aboutItems
        )

        return listOf(appCategory, contentPreferencesCategory, aboutCategory)
    }
}