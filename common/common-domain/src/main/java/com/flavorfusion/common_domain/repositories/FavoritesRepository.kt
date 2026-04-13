package com.flavorfusion.common_domain.repositories

import com.flavorfusion.common_domain.model.favorites.FavoriteItem
import com.flavorfusion.common_domain.model.favorites.ItemType
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavoritesFlow(): Flow<List<FavoriteItem>>
    fun getFavoriteIdsByTypeFlow(itemType: ItemType): Flow<Set<String>>
    suspend fun toggleFavorite(item: FavoriteItem)
}
