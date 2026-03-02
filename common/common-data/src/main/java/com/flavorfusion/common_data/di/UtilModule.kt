package com.flavorfusion.common_data.di

import com.flavorfusion.common_data.remote.model.DefaultResponseHandler
import com.flavorfusion.common_data.remote.model.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    fun providesResponseHandler(): ResponseHandler = DefaultResponseHandler()
}