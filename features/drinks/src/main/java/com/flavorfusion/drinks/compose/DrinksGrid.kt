package com.flavorfusion.drinks.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.model.drink.DrinkUi

@Composable
fun DrinksGrid(
    drinks: List<DrinkUi>,
    onDrinkClick: (DrinkUi) -> Unit
) {
    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 0.dp),
    ) {
        if (drinks.isEmpty()) {
            item {
                Text(text = "Empty list")
            }
        } else {
            itemsIndexed(
                items = drinks,
                key = { _, it -> it.drinkId }) { index, drink ->
                DrinkItem(
                    drink = drink,
                    onDrinkClick = onDrinkClick
                )
            }
        }
    }
}
