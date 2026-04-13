package com.flavorfusion.common_data.local_storage.room.entity

import androidx.room.Entity
import com.flavorfusion.common_domain.model.favorites.FavoriteItem
import com.flavorfusion.common_domain.model.favorites.ItemType

@Entity(tableName = "favorites", primaryKeys = ["id", "itemType"])
data class FavoriteEntity(
    val id: String,
    val name: String,
    val imageUrl: String,
    val itemType: String
)

fun FavoriteItem.toEntity() = FavoriteEntity(
    id = id,
    name = name,
    imageUrl = imageUrl,
    itemType = itemType.name
)

fun FavoriteEntity.toDomain() = FavoriteItem(
    id = id,
    name = name,
    imageUrl = imageUrl,
    itemType = ItemType.valueOf(itemType)
)
