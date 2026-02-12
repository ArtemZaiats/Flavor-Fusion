package com.flavorfusion.flavorfusion.navigation

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable data object DrinksScreen: Route()

    @SuppressLint("UnsafeOptInUsageError")
    @Serializable data class DrinkDetailsScreen(
        val drinkId: String,
    ): Route()
}