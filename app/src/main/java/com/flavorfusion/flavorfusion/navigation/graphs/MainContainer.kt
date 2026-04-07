package com.flavorfusion.flavorfusion.navigation.graphs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.flavorfusion.flavorfusion.navigation.bottom_bar.AppBottomBar
import com.flavorfusion.flavorfusion.navigation.Navigator
import com.flavorfusion.flavorfusion.navigation.Route

@Composable
fun MainContainer(
    navigator: Navigator,
    entries: List<NavEntry<NavKey>>
) {
    val currentRoute = navigator.state.backStacks[navigator.state.topLevelRoute]?.last()

    val showBottomBar = currentRoute == Route.Drinks ||
            currentRoute == Route.Recipes ||
            currentRoute == Route.Favorite ||
            currentRoute == Route.Settings ||
            currentRoute == Route.Home

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
                AppBottomBar(navigator = navigator)
            }
        }
    ) { paddingValues ->
        NavDisplay(
            entries = entries,
            onBack = { navigator.goBack() },
            modifier = Modifier.padding(paddingValues)
        )
    }
}