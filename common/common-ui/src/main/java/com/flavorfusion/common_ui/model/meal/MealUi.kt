package com.flavorfusion.common_ui.model.meal

import com.flavorfusion.common_domain.model.meals.Meal

data class MealUi(
    val mealName: String,
    val mealImage: String,
    val mealId: String
)

fun Meal.toUi() = MealUi(
    mealName = mealName,
    mealImage = mealImage,
    mealId = mealId
)

fun List<Meal>.toUi(): List<MealUi> = map { it.toUi() }
