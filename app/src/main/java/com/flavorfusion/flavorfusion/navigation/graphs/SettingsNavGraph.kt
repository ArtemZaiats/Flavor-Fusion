package com.flavorfusion.flavorfusion.navigation.graphs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.flavorfusion.flavorfusion.navigation.Route
import com.flavorfusion.flavorfusion.navigation.Screen

fun NavGraphBuilder.settingsNavGraph(navController: NavHostController) {
    navigation<Route.Settings>(startDestination = Screen.Settings) {
        settingsScreen()
    }
}

private fun NavGraphBuilder.settingsScreen() {
    composable<Screen.Settings> {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Settings")
        }
    }
}