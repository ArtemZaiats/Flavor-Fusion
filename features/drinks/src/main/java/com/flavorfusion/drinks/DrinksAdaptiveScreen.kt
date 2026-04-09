package com.flavorfusion.drinks

import androidx.compose.runtime.Composable
import com.flavorfusion.common_ui.compose.AdaptiveListDetailLayout
import com.flavorfusion.drinks.drinkDetails.DrinkDetailsScreen

@Composable
fun DrinksAdaptiveScreen(navigateToDrinkDetails: (String) -> Unit) {
    AdaptiveListDetailLayout(
        navigateToDetails = navigateToDrinkDetails,
        emptyDetailText = "Select a drink to see details",
        listContent = { onSelected -> DrinksScreen(navigateToDrinkDetails = onSelected) },
        detailContent = { drinkId, onBack -> DrinkDetailsScreen(drinkId = drinkId, onBackClick = onBack) }
    )
}
