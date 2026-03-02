package com.flavorfusion.common_data.remote.model.drinks

import com.flavorfusion.common_domain.model.drinks.DrinkDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrinkDetailsData(
    @SerialName("idDrink") val drinkId: String,
    @SerialName("strDrink") val drinkName: String,
    @SerialName("strDrinkAlternate") val drinkAlternate: String?,
    @SerialName("strTags") val tags: String?,
    @SerialName("strVideo") val videoUrl: String?,
    @SerialName("strCategory") val category: String,
    @SerialName("strIBA") val iba: String?,
    @SerialName("strAlcoholic") val alcoholic: String?,
    @SerialName("strGlass") val glass: String?,
    @SerialName("strInstructions") val instructions: String?,
    @SerialName("strInstructionsES") val instructionsES: String?,
    @SerialName("strInstructionsDE") val instructionsDE: String?,
    @SerialName("strInstructionsFR") val instructionsFR: String?,
    @SerialName("strInstructionsIT") val instructionsIT: String?,
    @SerialName("strInstructionsZH-HANS") val instructionsZH_HANS: String?,
    @SerialName("strInstructionsZH-HANT") val instructionsZH_HANT: String?,
    @SerialName("strDrinkThumb") val drinkImage: String?,
    @SerialName("strIngredient1") val ingredient1: String?,
    @SerialName("strIngredient2") val ingredient2: String?,
    @SerialName("strIngredient3") val ingredient3: String?,
    @SerialName("strIngredient4") val ingredient4: String?,
    @SerialName("strIngredient5") val ingredient5: String?,
    @SerialName("strIngredient6") val ingredient6: String?,
    @SerialName("strIngredient7") val ingredient7: String?,
    @SerialName("strIngredient8") val ingredient8: String?,
    @SerialName("strIngredient9") val ingredient9: String?,
    @SerialName("strIngredient10") val ingredient10: String?,
    @SerialName("strIngredient11") val ingredient11: String?,
    @SerialName("strIngredient12") val ingredient12: String?,
    @SerialName("strIngredient13") val ingredient13: String?,
    @SerialName("strIngredient14") val ingredient14: String?,
    @SerialName("strIngredient15") val ingredient15: String?,
    @SerialName("strMeasure1") val measure1: String?,
    @SerialName("strMeasure2") val measure2: String?,
    @SerialName("strMeasure3") val measure3: String?,
    @SerialName("strMeasure4") val measure4: String?,
    @SerialName("strMeasure5") val measure5: String?,
    @SerialName("strMeasure6") val measure6: String?,
    @SerialName("strMeasure7") val measure7: String?,
    @SerialName("strMeasure8") val measure8: String?,
    @SerialName("strMeasure9") val measure9: String?,
    @SerialName("strMeasure10") val measure10: String?,
    @SerialName("strMeasure11") val measure11: String?,
    @SerialName("strMeasure12") val measure12: String?,
    @SerialName("strMeasure13") val measure13: String?,
    @SerialName("strMeasure14") val measure14: String?,
    @SerialName("strMeasure15") val measure15: String?,
    @SerialName("strImageSource") val imageSource: String?,
    @SerialName("strImageAttribution") val imageAttribution: String?,
    @SerialName("strCreativeCommonsConfirmed") val creativeCommonsConfirmed: String?,
    @SerialName("dateModified") val dateModified: String?
)

fun DrinkDetailsData.toDomain(): DrinkDetails {
    val ingredients = mutableMapOf<String?, String?>()

    val ingredientList = listOf(
        ingredient1, ingredient2, ingredient3, ingredient4, ingredient5,
        ingredient6, ingredient7, ingredient8, ingredient9, ingredient10,
        ingredient11, ingredient12, ingredient13, ingredient14, ingredient15
    )

    val measureList = listOf(
        measure1, measure2, measure3, measure4, measure5,
        measure6, measure7, measure8, measure9, measure10,
        measure11, measure12, measure13, measure14, measure15
    )

    for (i in ingredientList.indices) {
        val ingredient = ingredientList[i]
        val measure = measureList[i]
        if (!ingredient.isNullOrBlank()) {
            ingredients[ingredient] = measure.orEmpty()
        }
    }

    return DrinkDetails(
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
        ingredients = ingredients.ifEmpty { null },
        imageSource = imageSource,
        imageAttribution = imageAttribution,
        creativeCommonsConfirmed = creativeCommonsConfirmed,
        dateModified = dateModified
    )
}