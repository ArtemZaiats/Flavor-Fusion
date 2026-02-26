package com.flavorfusion.flavorfusion.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.flavorfusion.flavorfusion.navigation.Route
import com.flavorfusion.flavorfusion.navigation.Screen
import com.flavorfusion.settings.SettingsScreen

fun NavGraphBuilder.settingsNavGraph(navController: NavHostController) {
    navigation<Route.Settings>(startDestination = Screen.Settings) {
        settingsScreen()
    }
}

private fun NavGraphBuilder.settingsScreen() {
    composable<Screen.Settings> {
        SettingsScreen()
    }
}