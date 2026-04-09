package com.flavorfusion.common_data.di

import android.content.Context
import com.flavorfusion.common_data.di.qualifiers.MealsClient
import com.flavorfusion.common_data.remote.retrofit_factory.MealsRetrofitFactory
import com.flavorfusion.common_data.remote.retrofit_factory.RetrofitFactory
import com.flavorfusion.common_data.remote.services.MealsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object MealsRemoteModule {

    @Provides
    @MealsClient
    fun providesMealsRetrofitFactory(@ApplicationContext context: Context): RetrofitFactory =
        MealsRetrofitFactory(context)

    @Provides
    @MealsClient
    fun provideMealsRetrofit(@MealsClient retrofitFactory: RetrofitFactory): Retrofit =
        retrofitFactory.createRetrofit()

    @Provides
    @MealsClient
    fun provideMealsApiService(@MealsClient retrofit: Retrofit): MealsApiService =
        retrofit.create(MealsApiService::class.java)
}