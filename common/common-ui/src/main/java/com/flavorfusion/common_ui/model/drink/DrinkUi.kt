package com.flavorfusion.common_ui.model.drink

import com.flavorfusion.common_domain.model.drinks.Drink

data class DrinkUi(
    val drinkName: String,
    val drinkImage: String,
    val drinkId: String,
    val isFavorite: Boolean = false
)

fun Drink.toUi() = DrinkUi(
    drinkName = drinkName,
    drinkImage = drinkImage,
    drinkId = drinkId
)

fun List<Drink>.toUi(): List<DrinkUi> = map { it.toUi() }