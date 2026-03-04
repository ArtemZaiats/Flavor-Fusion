package com.flavorfusion.settings.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsAlcoholic
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsAppTheme
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsGlass
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsLanguage
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsPrivacyPolicy
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsTermsOfUse

class SettingsDataPreviewProvider : PreviewParameterProvider<SettingsCategory> {
    override val values: Sequence<SettingsCategory>
        get() = sequenceOf(
            // App
            SettingsCategory(
                id = Category.APP.id,
                title = Category.APP.title,
                items = listOf(
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
            ),
            // CONTENT_PREFERENCES
            SettingsCategory(
                id = Category.CONTENT_PREFERENCES.id,
                title = Category.CONTENT_PREFERENCES.title,
                items = listOf(
                    MenuItem(
                        id = CategoryItem.SHOW_ALCOHOLIC.id,
                        title = "Show alcoholic drinks",
                        icon = AppIcons.SettingsGlass,
                        category = Category.CONTENT_PREFERENCES,
                        showSwitch = true,
                        isSwitchActive = true
                    ),
                )
            ),

            // ABOUT
            SettingsCategory(
                id = Category.ABOUT.id,
                title = Category.ABOUT.title,
                items = listOf(
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
            ),
        )
}