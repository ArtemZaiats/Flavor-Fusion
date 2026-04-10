package com.flavorfusion.common_domain.repositories

import com.flavorfusion.common_domain.model.meals.Meal
import com.flavorfusion.common_domain.model.meals.MealDetails
import com.flavorfusion.common_domain.model.Result
import kotlinx.coroutines.flow.Flow

interface MealsRepository {
    suspend fun getMealsByCategory(category: String): Result<List<Meal>?>
    suspend fun getMealById(id: String): Result<List<MealDetails>?>
    fun getMealByNameFlow(name: String): Flow<Result<List<Meal>?>>
}