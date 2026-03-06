package com.flavorfusion.settings.providers

import android.content.Context
import com.flavorfusion.common_ui.R
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsAlcoholic
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsAppTheme
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsGlass
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsLanguage
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsPrivacyPolicy
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsTermsOfUse
import com.flavorfusion.common_ui.utils.DataProvider
import com.flavorfusion.settings.model.Category
import com.flavorfusion.settings.model.CategoryItem
import com.flavorfusion.settings.model.MenuItem
import com.flavorfusion.settings.model.SettingsCategory
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SettingsCategoryProvider @Inject constructor(
    @param:ApplicationContext private val context: Context
) : DataProvider<List<SettingsCategory>> {
    override fun provideData(): List<SettingsCategory> {
        val appCategoriesItems = listOf(
            MenuItem(
                id = CategoryItem.LANGUAGE.id,
                isActive = false,
                title = context.getString(R.string.feature_settings_category_item_language),
                icon = AppIcons.SettingsLanguage,
                category = Category.APP,
            ),
            MenuItem(
                id = CategoryItem.APP_THEME.id,
                title = context.getString(R.string.feature_settings_category_item_app_theme),
                icon = AppIcons.SettingsAppTheme,
                category = Category.APP,
            ),
        )

        val contentPreferencesItems = listOf(
            MenuItem(
                id = CategoryItem.SHOW_ALCOHOLIC.id,
                title = context.getString(R.string.feature_settings_category_item_show_alcoholic),
                icon = AppIcons.SettingsGlass,
                category = Category.CONTENT_PREFERENCES,
                showSwitch = true,
                isSwitchActive = true
            ),
        )

        val aboutItems = listOf(
            MenuItem(
                id = CategoryItem.PRIVACY_POLICY.id,
                title = context.getString(R.string.feature_settings_category_item_privacy_policy),
                icon = AppIcons.SettingsPrivacyPolicy,
                category = Category.ABOUT,
            ),
            MenuItem(
                id = CategoryItem.TERMS_OF_USE.id,
                title = context.getString(R.string.feature_settings_category_item_terms_of_use),
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