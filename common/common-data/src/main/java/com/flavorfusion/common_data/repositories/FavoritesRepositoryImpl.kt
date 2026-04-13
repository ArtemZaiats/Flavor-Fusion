package com.flavorfusion.common_data.repositories

import com.flavorfusion.common_data.local_storage.room.dao.FavoriteDao
import com.flavorfusion.common_data.local_storage.room.entity.toDomain
import com.flavorfusion.common_data.local_storage.room.entity.toEntity
import com.flavorfusion.common_domain.model.favorites.FavoriteItem
import com.flavorfusion.common_domain.model.favorites.ItemType
import com.flavorfusion.common_domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao
) : FavoritesRepository {

    override fun getFavoritesFlow(): Flow<List<FavoriteItem>> =
        dao.getAllFavoritesFlow().map { entities ->
            entities.map { it.toDomain() }
        }

    override fun getFavoriteIdsByTypeFlow(itemType: ItemType): Flow<Set<String>> =
        dao.getFavoriteIdsByTypeFlow(itemType.name).map { it.toSet() }

    override suspend fun toggleFavorite(item: FavoriteItem) {
        val entity = item.toEntity()
        val exists = dao.exists(entity.id, entity.itemType) > 0
        if (exists) {
            dao.delete(entity.id, entity.itemType)
        } else {
            dao.insert(entity)
        }
    }
}
