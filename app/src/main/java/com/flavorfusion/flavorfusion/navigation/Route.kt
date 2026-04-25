package com.flavorfusion.flavorfusion.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {

    @Serializable
    data object Main : Route

    // Bottom navigation
    @Serializable
    data object Home : Route

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
    data class DrinkDetails(
        val drinkId: String,
    ) : Screen

    @Serializable
    data class MealDetails(
        val mealId: String
    ) : Screen

    @Serializable
    data object SettingsAppTheme : Screen

    @Serializable
    data object SettingsEditUser : Screen
}