package com.flavorfusion.flavorfusion.navigation

import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.NavEntry
import com.flavorfusion.drinks.DrinksAdaptiveScreen
import com.flavorfusion.drinks.drinkDetails.DrinkDetailsScreen
import com.flavorfusion.meals.mealDetails.MealDetailsScreen
import com.flavorfusion.home.HomeScreen
import com.flavorfusion.settings.SettingsScreen
import com.flavorfusion.settings.category.app_theme.AppThemeScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flavorfusion.flavorfusion.navigation.animation.screenMetadata
import com.flavorfusion.flavorfusion.navigation.animation.tabMetadata
import com.flavorfusion.meals.MealsAdaptiveScreen

fun appEntryProvider(navigator: Navigator): (NavKey) -> NavEntry<NavKey> = entryProvider {

    entry<Route.Home>(metadata = tabMetadata) {
        HomeScreen()
    }

    entry<Route.Recipes>(metadata = tabMetadata) {
        MealsAdaptiveScreen(navigateToMealDetails = { mealId ->
            navigator.navigate(Screen.MealDetails(mealId = mealId))
        })
    }

    entry<Route.Favorite>(metadata = tabMetadata) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Favorites")
        }
    }

    entry<Route.Settings>(metadata = tabMetadata) {
        SettingsScreen(
            navigateToAppTheme = {
                navigator.navigate(Screen.SettingsAppTheme)
            }
        )
    }

    entry<Route.Drinks>(metadata = tabMetadata) {
        DrinksAdaptiveScreen(
            navigateToDrinkDetails = { drinkId ->
                navigator.navigate(Screen.DrinkDetails(drinkId = drinkId))
            }
        )
    }

    entry<Screen.DrinkDetails>(metadata = screenMetadata) { key ->
        DrinkDetailsScreen(
            drinkId = key.drinkId,
            onBackClick = { navigator.goBack() }
        )
    }

    entry<Screen.MealDetails>(metadata = screenMetadata) { key ->
        MealDetailsScreen(
            mealId = key.mealId,
            onBackClick = { navigator.goBack() }
        )
    }

    entry<Screen.SettingsAppTheme>(metadata = screenMetadata) {
        AppThemeScreen(
            navigateBack = { navigator.goBack() }
        )
    }
}