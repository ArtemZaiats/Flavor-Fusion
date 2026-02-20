package com.flavorfusion.flavorfusion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.flavorfusion.flavorfusion.navigation.graphs.drinksNavGraph
import com.flavorfusion.flavorfusion.navigation.graphs.favoritesNavGraph
import com.flavorfusion.flavorfusion.navigation.graphs.mainContainer
import com.flavorfusion.flavorfusion.navigation.graphs.recipesNavGraph
import com.flavorfusion.flavorfusion.navigation.graphs.settingsNavGraph

@Composable
fun AppNavigation() {
    val rootNavController = rememberNavController()

    NavHost(navController = rootNavController, startDestination = Route.Main) {
        mainContainer { bottomNavHostController ->
            drinksNavGraph(navController = bottomNavHostController)
            favoritesNavGraph(navController = bottomNavHostController)
            recipesNavGraph(navController = bottomNavHostController)
            settingsNavGraph(navController = bottomNavHostController)
        }
    }
}