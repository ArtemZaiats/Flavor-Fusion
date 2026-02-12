package com.flavorfusion.drinks.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.model.drink.DrinkUi

@Composable
fun DrinksList(
    modifier: Modifier = Modifier,
    drinks: List<DrinkUi>,
    onDrinkClick: (DrinkUi) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
        state = rememberLazyListState()
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