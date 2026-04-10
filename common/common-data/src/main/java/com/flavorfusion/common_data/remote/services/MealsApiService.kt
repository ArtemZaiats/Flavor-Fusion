package com.flavorfusion.common_data.remote.services

import com.flavorfusion.common_data.remote.model.meals.CategoriesResponseData
import com.flavorfusion.common_data.remote.model.meals.MealDetailsResponseData
import com.flavorfusion.common_data.remote.model.meals.MealsResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiService {
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): Result<MealsResponseData>

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String): Result<MealDetailsResponseData>

    @GET("search.php")
    suspend fun getMealsByName(@Query("s") name: String): Result<MealsResponseData>

    @GET("categories.php")
    suspend fun getCategories(): Result<CategoriesResponseData>
}