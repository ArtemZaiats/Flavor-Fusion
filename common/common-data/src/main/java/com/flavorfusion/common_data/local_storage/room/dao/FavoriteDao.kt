package com.flavorfusion.common_data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flavorfusion.common_data.local_storage.room.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    fun getAllFavoritesFlow(): Flow<List<FavoriteEntity>>

    @Query("SELECT id FROM favorites WHERE itemType = :itemType")
    fun getFavoriteIdsByTypeFlow(itemType: String): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE id = :id AND itemType = :itemType")
    suspend fun delete(id: String, itemType: String)

    @Query("SELECT COUNT(*) FROM favorites WHERE id = :id AND itemType = :itemType")
    suspend fun exists(id: String, itemType: String): Int
}
