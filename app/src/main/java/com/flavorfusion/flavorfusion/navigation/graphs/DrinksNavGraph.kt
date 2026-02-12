package com.flavorfusion.flavorfusion.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.flavorfusion.drinks.drinkDetails.DrinkDetailsScreen
import com.flavorfusion.drinks.DrinksScreen
import com.flavorfusion.flavorfusion.navigation.Route

@Composable
fun DrinksNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.DrinksScreen
    ) {
        drinksScreen(navigateToDrinkDetails = navController::navigateToDrinkDetails)
        drinkDetailsScreen(navigateBack = navController::popBackStack)
    }
}

fun NavGraphBuilder.drinksScreen(
    navigateToDrinkDetails: (String) -> Unit
) {
    composable<Route.DrinksScreen> {
        DrinksScreen(
            navigateToDrinkDetails = navigateToDrinkDetails
        )
    }
}

fun NavGraphBuilder.drinkDetailsScreen(
    navigateBack: () -> Unit
) {
    composable<Route.DrinkDetailsScreen> {
        val args = it.toRoute<Route.DrinkDetailsScreen>()

        DrinkDetailsScreen(
            drinkId = args.drinkId,
            onBackClick = navigateBack
        )
    }
}

fun NavController.navigateToDrinkDetails(drinkId: String) {
    navigate(Route.DrinkDetailsScreen(drinkId = drinkId))
}