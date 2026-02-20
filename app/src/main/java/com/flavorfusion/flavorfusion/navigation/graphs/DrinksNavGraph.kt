package com.flavorfusion.flavorfusion.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.flavorfusion.drinks.drinkDetails.DrinkDetailsScreen
import com.flavorfusion.drinks.DrinksScreen
import com.flavorfusion.flavorfusion.navigation.Route
import com.flavorfusion.flavorfusion.navigation.Screen

fun NavGraphBuilder.drinksNavGraph(navController: NavHostController) {

    navigation<Route.Drinks>(startDestination = Screen.Drinks) {
        drinksScreen(navigateToDrinkDetails = navController::navigateToDrinkDetails)
        drinkDetailsScreen(navigateBack = navController::popBackStack)
    }
}

private fun NavGraphBuilder.drinksScreen(
    navigateToDrinkDetails: (String) -> Unit
) {
    composable<Screen.Drinks> {
        DrinksScreen(
            navigateToDrinkDetails = navigateToDrinkDetails
        )
    }
}

private fun NavGraphBuilder.drinkDetailsScreen(
    navigateBack: () -> Unit
) {
    composable<Screen.DrinkDetails> {
        val args = it.toRoute<Screen.DrinkDetails>()

        DrinkDetailsScreen(
            drinkId = args.drinkId,
            onBackClick = navigateBack
        )
    }
}

private fun NavController.navigateToDrinkDetails(drinkId: String) {
    navigate(Screen.DrinkDetails(drinkId = drinkId))
}