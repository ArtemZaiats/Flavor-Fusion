package com.flavorfusion.drinks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.flavorfusion.drinks.drinkDetails.DrinkDetailsScreen

private const val LARGE_SCREEN_WIDTH_DP = 840

@Composable
fun DrinksAdaptiveScreen(
    navigateToDrinkDetails: (String) -> Unit
) {
    val isLargeScreen = LocalConfiguration.current.screenWidthDp >= LARGE_SCREEN_WIDTH_DP

    if (isLargeScreen) {
        var selectedDrinkId by rememberSaveable { mutableStateOf<String?>(null) }

        Row(Modifier.fillMaxSize()) {
            // Left pane — drinks list (40%)
            Box(
                Modifier
                    .weight(0.4f)
                    .fillMaxHeight()
            ) {
                DrinksScreen(navigateToDrinkDetails = { drinkId ->
                    selectedDrinkId = drinkId
                })
            }

            VerticalDivider()

            // Right pane — drink details (60%)
            Box(
                Modifier
                    .weight(0.6f)
                    .fillMaxHeight()
            ) {
                val drinkId = selectedDrinkId
                if (drinkId != null) {
                    DrinkDetailsScreen(
                        drinkId = drinkId,
                        onBackClick = { selectedDrinkId = null }
                    )
                } else {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Select a drink to see details")
                    }
                }
            }
        }
    } else {
        DrinksScreen(navigateToDrinkDetails = navigateToDrinkDetails)
    }
}
