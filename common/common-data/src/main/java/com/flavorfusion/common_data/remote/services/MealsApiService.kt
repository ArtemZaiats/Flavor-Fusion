package com.flavorfusion.common_data.remote.services

import com.flavorfusion.common_data.remote.model.meals.MealsResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiService {
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String="Seafood"): Result<MealsResponseData>

//    @GET("lookup.php")
//    suspend fun getMealById(@Query("i") id: String): Result<MealDetailsResponseData>
}