package com.zaiatsdevelopment.common_data.remote.model.drinks

import com.flavorfusion.common_domain.model.drinks.Drink
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrinkData(
    @SerialName("strDrink") val drinkName: String,
    @SerialName("strDrinkThumb") val drinkImage: String,
    @SerialName("idDrink") val drinkId: String
)

@Serializable
data class DrinksResponseData(
    @SerialName("drinks") val drinks: List<DrinkData>
)

fun DrinkData.toDomain() = Drink(
    drinkName = drinkName,
    drinkImage = drinkImage,
    drinkId = drinkId
)

fun DrinksResponseData.toDomain() = drinks.map { it.toDomain() }