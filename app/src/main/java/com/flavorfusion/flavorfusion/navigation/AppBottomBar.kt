package com.flavorfusion.flavorfusion.navigation

import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.flavorfusion.flavorfusion.R

@Composable
fun AppBottomBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
        containerColor = Color.Black,
        windowInsets = NavigationBarDefaults.windowInsets.only(WindowInsetsSides.Horizontal)
    ) {
        bottomNavigationItems.forEach { item ->
            val isSelected = navBackStackEntry
                ?.destination
                ?.hierarchy
                ?.any { navDestination ->
                    when (item.route) {
                        is Route.Drinks -> navDestination.hasRoute<Route.Drinks>()
                        is Route.Recipes -> navDestination.hasRoute<Route.Recipes>()
                        is Route.Favorite -> navDestination.hasRoute<Route.Favorite>()
                        is Route.Settings -> navDestination.hasRoute<Route.Settings>()
                        else -> false
                    }
                } == true

            NavigationBarItem(
                modifier = Modifier.padding(vertical = 8.dp),
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = if (isSelected) {
                            Color(0xFF5770FF)
                        } else {
                            Color.Gray
                        }
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (isSelected) {
                            Color(0xFF5770FF)
                        } else {
                            Color.Gray
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}


data class BottomNavigationItem(
    val title: String,
    val icon: Int,
    val route: Route
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        title = "Recipes",
        icon = R.drawable.ic_food_navbar,
        route = Route.Recipes
    ),
    BottomNavigationItem(
        title = "Drinks",
        icon = R.drawable.ic_cocktail_navbar,
        route = Route.Drinks
    ),
    BottomNavigationItem(
        title = "Favorite",
        icon = R.drawable.ic_favorite_navbar,
        route = Route.Favorite
    ),
    BottomNavigationItem(
        title = "Settings",
        icon = R.drawable.ic_settings_navbar,
        route = Route.Settings
    )
)
