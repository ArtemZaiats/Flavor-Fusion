package com.flavorfusion.flavorfusion.navigation

import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.NavEntry
import com.flavorfusion.drinks.DrinksScreen
import com.flavorfusion.drinks.drinkDetails.DrinkDetailsScreen
import com.flavorfusion.settings.SettingsScreen
import com.flavorfusion.settings.category.app_theme.AppThemeScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

fun appEntryProvider(navigator: Navigator): (NavKey) -> NavEntry<NavKey> = entryProvider {
    entry<Screen.Drinks> {
        DrinksScreen(
            navigateToDrinkDetails = { drinkId ->
                navigator.navigate(Screen.DrinkDetails(drinkId = drinkId))
            }
        )
    }

    entry<Screen.DrinkDetails> { key ->
        DrinkDetailsScreen(
            drinkId = key.drinkId,
            onBackClick = { navigator.goBack() }
        )
    }

    entry<Screen.Recipes> {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Recipes")
        }
    }

    entry<Screen.Favorites> {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Favorites")
        }
    }

    entry<Screen.Settings> {
        SettingsScreen(
            navigateToAppTheme = {
                navigator.navigate(Screen.SettingsAppTheme)
            }
        )
    }

    entry<Screen.SettingsAppTheme> {
        AppThemeScreen(
            navigateBack = { navigator.goBack() }
        )
    }

    entry<Route.Drinks> {
        DrinksScreen(
            navigateToDrinkDetails = { drinkId ->
                navigator.navigate(Screen.DrinkDetails(drinkId = drinkId))
            }
        )
    }

    entry<Route.Recipes> {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Recipes")
        }
    }

    entry<Route.Favorite> {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Favorites")
        }
    }

    entry<Route.Settings> {
        SettingsScreen(
            navigateToAppTheme = {
                navigator.navigate(Screen.SettingsAppTheme)
            }
        )
    }
}