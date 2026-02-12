package com.flavorfusion.common_domain.interactors

import com.flavorfusion.common_domain.model.drinks.Drink
import com.flavorfusion.common_domain.model.drinks.DrinkDetails
import com.flavorfusion.common_domain.repositories.DrinksRepository
import javax.inject.Inject

class DrinksInteractor @Inject constructor(
    private val drinksRepository: DrinksRepository
) {

    suspend fun getDrinksByAlcoholic(alcoholic: String) = drinksRepository.getDrinksByAlcoholic(alcoholic)
    suspend fun getDrinkById(id: String) = drinksRepository.getDrinkById(id)
    suspend fun getDrinkByName(name: String) = drinksRepository.getDrinkByName(name)
}