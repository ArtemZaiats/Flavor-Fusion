package com.flavorfusion.meals

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
import com.flavorfusion.common_ui.utils.Constants.LARGE_SCREEN_WIDTH_DP
import com.flavorfusion.meals.mealDetails.MealDetailsScreen

@Composable
fun MealsAdaptiveScreen(
    navigateToMealDetails: (String) -> Unit
) {
    val isLargeScreen = LocalConfiguration.current.screenWidthDp >= LARGE_SCREEN_WIDTH_DP

    if (isLargeScreen) {
        var selectedMealId by rememberSaveable { mutableStateOf<String?>(null) }

        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight()
            ) {
                MealsScreen(
                    navigateToMealDetails = { mealId ->
                        selectedMealId = mealId
                    }
                )
            }

            VerticalDivider()

            Box(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxHeight()
            ) {
                val mealId = selectedMealId
                if (mealId != null) {
                    MealDetailsScreen(
                        mealId = mealId,
                        onBackClick = { selectedMealId = null }
                    )
                } else {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Select a meal to see details")
                    }
                }
            }
        }
    } else {
        MealsScreen(navigateToMealDetails = navigateToMealDetails)
    }
}