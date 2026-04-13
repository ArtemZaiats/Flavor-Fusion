package com.flavorfusion.drinks.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.model.drink.DrinkUi
import com.flavorfusion.common_ui.R

@Composable
fun DrinksGrid(
    drinks: List<DrinkUi>,
    onDrinkClick: (DrinkUi) -> Unit,
    onFavoriteClick: (DrinkUi) -> Unit
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
        if (drinks.isEmpty()) {
            item {
                Text(text = stringResource(R.string.feature_drinks_empty_list))
            }
        } else {
            items(
                items = drinks,
                key = { it.drinkId }) { drink ->
                DrinkItem(
                    drink = drink,
                    onDrinkClick = onDrinkClick,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}
