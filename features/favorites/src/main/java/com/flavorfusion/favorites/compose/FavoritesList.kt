package com.flavorfusion.favorites.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.compose.ListItem
import com.flavorfusion.common_ui.model.favorites.FavoriteItemUi

@Composable
fun FavoritesList(
    favorites: List<FavoriteItemUi>,
    onRemoveClick: (FavoriteItemUi) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = modifier
    ) {
        items(
            items = favorites,
            key = { "${it.itemType.name}:${it.id}" }
        ) { item ->
            ListItem(
                item = item,
                name = item.name,
                isFavorite = true,
                image = item.imageUrl,
                onItemClick = {},
                onFavoriteClick = onRemoveClick
            )
        }
    }
}
