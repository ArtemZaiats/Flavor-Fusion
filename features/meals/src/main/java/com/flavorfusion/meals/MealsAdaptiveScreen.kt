package com.flavorfusion.meals

import androidx.compose.runtime.Composable
import com.flavorfusion.common_ui.compose.AdaptiveListDetailLayout
import com.flavorfusion.meals.mealDetails.MealDetailsScreen

@Composable
fun MealsAdaptiveScreen(navigateToMealDetails: (String) -> Unit) {
    AdaptiveListDetailLayout(
        navigateToDetails = navigateToMealDetails,
        emptyDetailText = "Select a meal to see details",
        listContent = { onSelected -> MealsScreen(navigateToMealDetails = onSelected) },
        detailContent = { mealId, onBack -> MealDetailsScreen(mealId = mealId, onBackClick = onBack) }
    )
}
