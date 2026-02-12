package com.flavorfusion.drinks.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.model.drink.DrinkUi

@Composable
fun DrinksGrid(
    modifier: Modifier = Modifier,
    drinks: List<DrinkUi>,
    onDrinkClick: (DrinkUi) -> Unit
) {
    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
    ) {
        items(drinks) {
            DrinkItem(
                drink = it,
                onDrinkClick = onDrinkClick
            )
        }
        item {
            Spacer(modifier = Modifier.height(96.dp))
        }
    }
}
