package com.flavorfusion.meals.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.R
import com.flavorfusion.common_ui.model.meal.MealUi

@Composable
fun MealsGrid(
    meals: List<MealUi>,
    onMealClick: (MealUi) -> Unit,
    onFavoriteClick: (MealUi) -> Unit
) {
    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        if (meals.isEmpty()) {
            item {
                Text(text = stringResource(R.string.feature_meals_empty_list))
            }
        } else {
            items(
                items = meals,
                key = { it.mealId }
            ) { meal ->
                MealItem(
                    meal = meal,
                    onMealClick = onMealClick,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}
