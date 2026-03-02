package com.flavorfusion.common_data.remote.services

import com.flavorfusion.common_data.remote.model.drinks.DrinkDetailsResponseData
import com.flavorfusion.common_data.remote.model.drinks.DrinksResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinksApiService {
    @GET("filter.php")
    suspend fun getDrinksByAlcoholic(@Query("a") category: String): Result<DrinksResponseData>

    @GET("lookup.php")
    suspend fun getDrinkById(@Query("i") id: String): Result<DrinkDetailsResponseData>

    @GET("search.php")
    suspend fun getDrinksByName(@Query("s") name: String): Result<DrinksResponseData>
}