package com.flavorfusion.common_data.remote.retrofit_factory

import android.content.Context
import com.flavorfusion.common_data.BuildConfig
import com.flavorfusion.common_data.remote.retrofit_factory.clients.OKHttpClient
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val json = Json {
    ignoreUnknownKeys = true
}

class DrinksRetrofitFactory(private val context: Context) : RetrofitFactory {

    override fun createRetrofit(): Retrofit {
        val client = OKHttpClient(context).provideOkHttpClient()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.COCKTAILS_BASE_URL)
            .client(client)
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
            .build()
    }
}