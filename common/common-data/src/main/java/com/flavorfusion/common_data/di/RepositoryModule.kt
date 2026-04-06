package com.flavorfusion.common_data.di

import com.flavorfusion.common_domain.repositories.AuthRepository
import com.flavorfusion.common_domain.repositories.DrinksRepository
import com.flavorfusion.common_domain.repositories.SettingsRepository
import com.flavorfusion.common_data.di.qualifiers.DrinksClient
import com.flavorfusion.common_data.local_storage.shared_preferences.DataStoreHelper
import com.flavorfusion.common_data.repositories.AuthRepositoryImpl
import io.github.jan.supabase.SupabaseClient
import com.flavorfusion.common_data.remote.model.ResponseHandler
import com.flavorfusion.common_data.remote.services.DrinksApiService
import com.flavorfusion.common_data.repositories.DrinksRepositoryImpl
import com.flavorfusion.common_data.repositories.SettingsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesDrinksRepository(
        @DrinksClient service: DrinksApiService,
        responseHandler: ResponseHandler
    ): DrinksRepository {
        return DrinksRepositoryImpl(service, responseHandler)
    }

    @Provides
    fun providesSettingsRepository(
        dataStoreHelper: DataStoreHelper
    ): SettingsRepository {
        return SettingsRepositoryImpl(dataStoreHelper)
    }

    @Provides
    @Singleton
    fun providesAuthRepository(
        supabaseClient: SupabaseClient
    ): AuthRepository {
        return AuthRepositoryImpl(supabaseClient)
    }
}