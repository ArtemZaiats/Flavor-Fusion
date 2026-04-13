package com.flavorfusion.common_domain.model.favorites

data class FavoriteItem(
    val id: String,
    val name: String,
    val imageUrl: String,
    val itemType: ItemType
)

enum class ItemType {
    MEAL, DRINK
}
