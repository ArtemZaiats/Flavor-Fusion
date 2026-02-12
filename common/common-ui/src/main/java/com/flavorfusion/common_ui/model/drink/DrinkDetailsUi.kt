package com.flavorfusion.common_ui.model.drink

import com.flavorfusion.common_domain.model.drinks.DrinkDetails

data class DrinkDetailsUi(
    val drinkId: String = "",
    val drinkName: String = "",
    val drinkAlternate: String? = null,
    val tags: String? = null,
    val videoUrl: String? = null,
    val category: String = "",
    val iba: String? = null,
    val alcoholic: String? = null,
    val glass: String? = null,
    val instructions: String? = null,
    val instructionsES: String? = null,
    val instructionsDE: String? = null,
    val instructionsFR: String? = null,
    val instructionsIT: String? = null,
    val instructionsZH_HANS: String? = null,
    val instructionsZH_HANT: String? = null,
    val drinkImage: String? = null,
    val ingredients: Map<String?, String?>? = null,
    val imageSource: String? = null,
    val imageAttribution: String? = null,
    val creativeCommonsConfirmed: String? = null,
    val dateModified: String? = null
)

fun DrinkDetails.toUi() = DrinkDetailsUi(
    drinkId = drinkId,
    drinkName = drinkName,
    drinkAlternate = drinkAlternate,
    tags = tags,
    videoUrl = videoUrl,
    category = category,
    iba = iba,
    alcoholic = alcoholic,
    glass = glass,
    instructions = instructions,
    instructionsES = instructionsES,
    instructionsDE = instructionsDE,
    instructionsFR = instructionsFR,
    instructionsIT = instructionsIT,
    instructionsZH_HANS = instructionsZH_HANS,
    instructionsZH_HANT = instructionsZH_HANT,
    drinkImage = drinkImage,
    ingredients = ingredients,
    imageSource = imageSource,
    imageAttribution = imageAttribution,
    creativeCommonsConfirmed = creativeCommonsConfirmed,
    dateModified = dateModified,
)

fun List<DrinkDetails>.toUi(): List<DrinkDetailsUi> = map { it.toUi() }