package com.flavorfusion.common_ui.model.meal

import com.flavorfusion.common_domain.model.meals.MealCategory

data class MealCategoryUi(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val isSelected: Boolean = false
)

fun MealCategory.toUi() = MealCategoryUi(
    id = id,
    name = name,
    imageUrl = imageUrl,
    description = description
)

fun List<MealCategory>.toUi(): List<MealCategoryUi> = map { it.toUi() }