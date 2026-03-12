package com.flavorfusion.flavorfusion.navigation.bottom_bar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.flavorfusion.common_ui.theme.FlavorFusionTheme
import com.flavorfusion.flavorfusion.R
import com.flavorfusion.flavorfusion.navigation.Navigator
import com.flavorfusion.flavorfusion.navigation.Route

@Composable
fun AppBottomBar(
    navigator: Navigator
) {
    NavigationBar(
        containerColor = FlavorFusionTheme.colors.backgroundSecondary
    ) {
        bottomNavigationItems.forEach { item ->
            val isSelected = item.route == navigator.state.topLevelRoute

            val tint = if (isSelected) FlavorFusionTheme.colors.colorPrimary
            else FlavorFusionTheme.colors.contentSecondary

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navigator.navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = tint
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = tint
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
