package com.flavorfusion.common_data.di

import android.content.Context
import androidx.room.Room
import com.flavorfusion.common_data.local_storage.room.FavoriteDatabase
import com.flavorfusion.common_data.local_storage.room.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideFavoriteDatabase(
        @ApplicationContext context: Context
    ): FavoriteDatabase = Room.databaseBuilder(
        context,
        FavoriteDatabase::class.java,
        "flavor_fusion_favorites.db"
    ).build()

    @Provides
    @Singleton
    fun provideFavoriteDao(db: FavoriteDatabase): FavoriteDao = db.favoriteDao()
}
