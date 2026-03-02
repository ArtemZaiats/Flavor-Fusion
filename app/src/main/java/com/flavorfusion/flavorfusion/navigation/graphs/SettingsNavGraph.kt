package com.flavorfusion.flavorfusion.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.flavorfusion.flavorfusion.navigation.Route
import com.flavorfusion.flavorfusion.navigation.Screen
import com.flavorfusion.settings.SettingsScreen
import com.flavorfusion.settings.category.app_theme.AppThemeScreen

fun NavGraphBuilder.settingsNavGraph(navController: NavHostController) {
    navigation<Route.Settings>(startDestination = Screen.Settings) {
        settingsScreen(navController::navigateToAppTheme)
        settingsAppThemeScreen(navController::popBackStack)
    }
}

private fun NavGraphBuilder.settingsScreen(
    navigateToAppTheme: () -> Unit
) {
    composable<Screen.Settings> {
        SettingsScreen(
            navigateToAppTheme = navigateToAppTheme
        )
    }
}

private fun NavGraphBuilder.settingsAppThemeScreen(
    navigateBack: () -> Unit
) {
    composable<Screen.SettingsAppTheme> {
        AppThemeScreen(
            navigateBack = navigateBack
        )
    }
}

private fun NavController.navigateToAppTheme() {
    navigate(Screen.SettingsAppTheme)
}