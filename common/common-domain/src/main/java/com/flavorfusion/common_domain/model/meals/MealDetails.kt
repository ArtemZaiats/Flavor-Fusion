package com.flavorfusion.common_domain.model.meals

data class MealDetails(
    val mealId: String,
    val mealName: String,
    val mealAlternate: String?,
    val category: String,
    val area: String?,
    val instructions: String?,
    val mealImage: String?,
    val tags: String?,
    val videoUrl: String?,
    val ingredients: Map<String?, String?>?,
    val source: String?,
    val imageSource: String?,
    val creativeCommonsConfirmed: String?,
    val dateModified: String?
)