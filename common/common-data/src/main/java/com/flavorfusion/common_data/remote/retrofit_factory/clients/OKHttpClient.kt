package com.flavorfusion.common_data.remote.retrofit_factory.clients

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.flavorfusion.common_data.BuildConfig
import com.flavorfusion.common_data.remote.retrofit_factory.interceptors.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OKHttpClient(private val context: Context) : OkHttpClient() {
    val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = Builder()
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

        return okHttpBuilder.build()
    }
}