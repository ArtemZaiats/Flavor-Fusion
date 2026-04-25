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
import com.flavorfusion.settings.category.edit_user.EditUserScreen
import com.flavorfusion.flavorfusion.navigation.animation.screenMetadata
import com.flavorfusion.flavorfusion.navigation.animation.tabMetadata
import com.flavorfusion.favorites.FavoritesScreen
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
        FavoritesScreen()
    }

    entry<Route.Settings>(metadata = tabMetadata) {
        SettingsScreen(
            navigateToAppTheme = {
                navigator.navigate(Screen.SettingsAppTheme)
            },
            navigateToEditUser = {
                navigator.navigate(Screen.SettingsEditUser)
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

    entry<Screen.SettingsEditUser>(metadata = screenMetadata) {
        EditUserScreen(
            navigateBack = { navigator.goBack() }
        )
    }
}