package com.zaiatsdevelopment.common_data.di

import com.flavorfusion.common_domain.repositories.DrinksRepository
import com.zaiatsdevelopment.common_data.di.qualifiers.DrinksClient
import com.zaiatsdevelopment.common_data.remote.model.ResponseHandler
import com.zaiatsdevelopment.common_data.remote.services.DrinksApiService
import com.zaiatsdevelopment.common_data.repositories.DrinksRepositoryImpl
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
}