package com.flavorfusion.common_domain.interactors

import com.flavorfusion.common_domain.model.favorites.FavoriteItem
import com.flavorfusion.common_domain.model.favorites.ItemType
import com.flavorfusion.common_domain.repositories.FavoritesRepository
import javax.inject.Inject

class FavoritesInteractor @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    fun getFavoritesFlow() = favoritesRepository.getFavoritesFlow()
    fun getFavoriteIdsByTypeFlow(itemType: ItemType) = favoritesRepository.getFavoriteIdsByTypeFlow(itemType)
    suspend fun toggleFavorite(item: FavoriteItem) = favoritesRepository.toggleFavorite(item)
}
