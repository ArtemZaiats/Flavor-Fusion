package com.flavorfusion.flavorfusion.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.flavorfusion.flavorfusion.navigation.graphs.DrinksNavHost

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController
) {
    NavHost(navController = rootNavController, startDestination = "drinks") {
        composable("recipes") {
            RecipesNavHost(modifier = modifier)
        }
        composable("drinks") {
            DrinksNavHost()
        }
        composable("favorite") {
            FavoritesNavHost(modifier = modifier)
        }
    }
}