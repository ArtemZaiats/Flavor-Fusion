package com.flavorfusion.common_data.remote.model.meals

import com.flavorfusion.common_domain.model.meals.MealCategory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealCategoryData(
    @SerialName("idCategory") val id: String,
    @SerialName("strCategory") val name: String,
    @SerialName("strCategoryThumb") val imageUrl: String,
    @SerialName("strCategoryDescription") val description: String
)

@Serializable
data class CategoriesResponseData(
    @SerialName("categories") val categories: List<MealCategoryData>
)

fun MealCategoryData.toDomain() = MealCategory(
    id = id,
    name = name,
    imageUrl = imageUrl,
    description = description
)

fun CategoriesResponseData.toDomain() = categories.map { it.toDomain() }