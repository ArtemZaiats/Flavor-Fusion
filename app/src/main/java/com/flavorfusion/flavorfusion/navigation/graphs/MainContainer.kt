package com.flavorfusion.flavorfusion.navigation.graphs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flavorfusion.flavorfusion.navigation.AppBottomBar
import com.flavorfusion.flavorfusion.navigation.Route

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun NavGraphBuilder.mainContainer(
    content: NavGraphBuilder.(NavHostController) -> Unit
) {
    composable<Route.Main> {
        val bottomNavHostController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                AppBottomBar(navController = bottomNavHostController)
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