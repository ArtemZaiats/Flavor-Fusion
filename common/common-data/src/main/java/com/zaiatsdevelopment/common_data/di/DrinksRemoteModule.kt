package com.zaiatsdevelopment.common_data.di

import android.content.Context
import com.zaiatsdevelopment.common_data.di.qualifiers.DrinksClient
import com.zaiatsdevelopment.common_data.remote.retrofit_factory.DrinksRetrofitFactory
import com.zaiatsdevelopment.common_data.remote.retrofit_factory.RetrofitFactory
import com.zaiatsdevelopment.common_data.remote.services.DrinksApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DrinksRemoteModule {

    @Provides
    @DrinksClient
    fun providesDrinksRetrofitFactory(
        @ApplicationContext context: Context
    ): RetrofitFactory {
        return DrinksRetrofitFactory(context)
    }

    @Provides
    @DrinksClient
    fun provideDrinksRetrofit(@DrinksClient retrofitFactory: RetrofitFactory): Retrofit {
        return retrofitFactory.createRetrofit()
    }

    @Provides
    @DrinksClient
    fun provideDrinksApiService(@DrinksClient retrofit: Retrofit): DrinksApiService {
        return retrofit.create(DrinksApiService::class.java)
    }
}
