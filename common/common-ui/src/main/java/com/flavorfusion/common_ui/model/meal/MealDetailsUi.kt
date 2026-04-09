package com.flavorfusion.common_ui.model.meal

import com.flavorfusion.common_domain.model.meals.MealDetails

data class MealDetailsUi(
    val mealId: String = "",
    val mealName: String = "",
    val mealAlternate: String? = null,
    val category: String = "",
    val area: String? = null,
    val instructions: String? = null,
    val mealImage: String? = null,
    val tags: String? = null,
    val videoUrl: String? = null,
    val ingredients: Map<String?, String?>? = null,
    val source: String? = null,
    val imageSource: String? = null,
    val creativeCommonsConfirmed: String? = null,
    val dateModified: String? = null
)

fun MealDetails.toUi() = MealDetailsUi(
    mealId = mealId,
    mealName = mealName,
    mealAlternate = mealAlternate,
    category = category,
    area = area,
    instructions = instructions,
    mealImage = mealImage,
    tags = tags,
    videoUrl = videoUrl,
    ingredients = ingredients,
    source = source,
    imageSource = imageSource,
    creativeCommonsConfirmed = creativeCommonsConfirmed,
    dateModified = dateModified
)

fun List<MealDetails>.toUi(): List<MealDetailsUi> = map { it.toUi() }
