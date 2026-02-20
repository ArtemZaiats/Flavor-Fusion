package com.flavorfusion.flavorfusion.navigation.graphs

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavDestination.Companion.hasRoute
import com.flavorfusion.flavorfusion.navigation.AppBottomBar
import com.flavorfusion.flavorfusion.navigation.Route
import com.flavorfusion.flavorfusion.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun NavGraphBuilder.mainContainer(
    content: NavGraphBuilder.(NavHostController) -> Unit
) {
    composable<Route.Main> {
        val bottomNavHostController = rememberNavController()
        val navBackStackEntry by bottomNavHostController.currentBackStackEntryAsState()
        val destination = navBackStackEntry?.destination

        val showBottomBar = destination?.hasRoute<Screen.Drinks>() == true ||
                destination?.hasRoute<Screen.Recipes>() == true ||
                destination?.hasRoute<Screen.Favorites>() == true ||
                destination?.hasRoute<Screen.Settings>() == true

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                AnimatedVisibility(
                    visible = showBottomBar,
                    enter = slideInVertically(
                        animationSpec = tween(300),
                        initialOffsetY = { it },
                    ),
                    exit = slideOutVertically(
                        animationSpec = tween(300),
                        targetOffsetY = { it },
                    ),
                ) {
                    AppBottomBar(navController = bottomNavHostController)
                }
            }
        ) {
            NavHost(
                navController = bottomNavHostController,
                startDestination = Route.Drinks,
            ) {
                content.invoke(this, bottomNavHostController)
            }
        }
    }
}