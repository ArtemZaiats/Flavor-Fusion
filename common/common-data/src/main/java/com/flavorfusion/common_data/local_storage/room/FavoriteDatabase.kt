package com.flavorfusion.common_data.local_storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flavorfusion.common_data.local_storage.room.dao.FavoriteDao
import com.flavorfusion.common_data.local_storage.room.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
