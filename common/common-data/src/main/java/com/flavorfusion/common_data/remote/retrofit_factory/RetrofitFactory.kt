package com.flavorfusion.common_data.remote.retrofit_factory

import retrofit2.Retrofit

interface RetrofitFactory {
    fun createRetrofit(): Retrofit
}