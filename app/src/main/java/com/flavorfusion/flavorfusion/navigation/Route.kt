package com.flavorfusion.flavorfusion.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

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
sealed interface Screen {
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
}