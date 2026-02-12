package com.zaiatsdevelopment.common_data.remote.retrofit_factory

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.flavorfusion.common_data.BuildConfig
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.zaiatsdevelopment.common_data.remote.retrofit_factory.interceptors.NetworkConnectionInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

val json = Json {
    ignoreUnknownKeys = true
}

class DrinksRetrofitFactory(private val context: Context): RetrofitFactory {

    override fun createRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val okHttpBuilder = OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(NetworkConnectionInterceptor(context))

        if (BuildConfig.DEBUG) {
            okHttpBuilder.apply {
                addInterceptor(loggingInterceptor)
                addInterceptor(ChuckerInterceptor(context))
            }
        }

        val client = okHttpBuilder.build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
            .build()


    }
}