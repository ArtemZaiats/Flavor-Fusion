package com.flavorfusion.common_domain.repositories

import com.flavorfusion.common_domain.model.meals.Meal
import com.flavorfusion.common_domain.model.meals.MealDetails
import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_domain.model.meals.MealCategory
import kotlinx.coroutines.flow.Flow

interface MealsRepository {
    suspend fun getMealsByCategory(category: String): Result<List<Meal>?>
    suspend fun getMealById(id: String): Result<List<MealDetails>?>
    suspend fun getCategories(): Result<List<MealCategory>?>
    fun getMealByNameFlow(name: String): Flow<Result<List<Meal>?>>
}