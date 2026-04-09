package com.flavorfusion.common_data.remote.model.meals

import com.flavorfusion.common_domain.model.meals.Meal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealData(
    @SerialName("strMeal") val mealName: String,
    @SerialName("strMealThumb") val mealImage: String,
    @SerialName("idMeal") val mealId: String
)

@Serializable
data class MealsResponseData(
    @SerialName("meals") val meals: List<MealData>
)

fun MealData.toDomain() = Meal(
    mealName = mealName,
    mealImage = mealImage,
    mealId = mealId
)

fun MealsResponseData.toDomain() = meals.map { it.toDomain() }