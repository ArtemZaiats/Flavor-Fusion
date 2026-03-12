package com.flavorfusion.flavorfusion.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {

    @Serializable
    data object Main : Route

    // Bottom navigation
    @Serializable
    data object Drinks : Route

    @Serializable
    data object Recipes : Route

    @Serializable
    data object Favorite : Route

    @Serializable
    data object Settings : Route

}

// Screens
sealed interface Screen : NavKey {
    @Serializable
    data object Drinks : Screen

    @Serializable
    data class DrinkDetails(
        val drinkId: String,
    ) : Screen

    @Serializable
    data object Favorites : Screen

    @Serializable
    data object Recipes : Screen

    @Serializable
    data object Settings : Screen

    @Serializable
    data object SettingsAppTheme : Screen
}