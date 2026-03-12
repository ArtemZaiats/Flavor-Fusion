package com.flavorfusion.flavorfusion.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.flavorfusion.flavorfusion.navigation.graphs.MainContainer

@Composable
fun AppNavigation() {
    val navigationState = rememberNavigationState(
        startRoute = Route.Drinks,
        topLevelRoutes = setOf(
            Route.Recipes,
            Route.Drinks,
            Route.Favorite,
            Route.Settings
        )
    )

    val navigator = remember { Navigator(navigationState) }
    val entryProvider = appEntryProvider(navigator)

    MainContainer(
        navigator = navigator,
        entries = navigationState.toEntries(entryProvider)
    )
}
