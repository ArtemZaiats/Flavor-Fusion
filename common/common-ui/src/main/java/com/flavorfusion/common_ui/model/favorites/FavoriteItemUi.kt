package com.flavorfusion.common_ui.model.favorites

import com.flavorfusion.common_domain.model.favorites.FavoriteItem
import com.flavorfusion.common_domain.model.favorites.ItemType

data class FavoriteItemUi(
    val id: String,
    val name: String,
    val imageUrl: String,
    val itemType: ItemType
)

fun FavoriteItem.toUi() = FavoriteItemUi(
    id = id,
    name = name,
    imageUrl = imageUrl,
    itemType = itemType
)

fun List<FavoriteItem>.toUi(): List<FavoriteItemUi> = map { it.toUi() }
