package com.flavorfusion.common_ui.model

import com.flavorfusion.common_ui.model.drink.DrinkDetailsUi
import com.flavorfusion.common_ui.model.meal.MealDetailsUi

data class ItemDetailsUi(
    val name: String = "",
    val image: String? = null,
    val category: String = "",
    val area: String? = null,
    val tags: String? = null,
    val instructions: String? = null,
    val videoUrl: String? = null,
    val ingredients: Map<String?, String?>? = null
)

fun MealDetailsUi.toItemDetailsUi() = ItemDetailsUi(
    name = mealName,
    image = mealImage,
    category = category,
    area = area,
    tags = tags,
    instructions = instructions,
    videoUrl = videoUrl,
    ingredients = ingredients
)

fun DrinkDetailsUi.toItemDetailsUi() = ItemDetailsUi(
    name = drinkName,
    image = drinkImage,
    category = category,
    area = null,
    tags = tags,
    instructions = instructions,
    videoUrl = videoUrl,
    ingredients = ingredients
)
