package com.flavorfusion.common_domain.interactors

import com.flavorfusion.common_domain.repositories.DrinksRepository
import javax.inject.Inject

class DrinksInteractor @Inject constructor(
    private val drinksRepository: DrinksRepository
) {
    suspend fun getDrinksByAlcoholic(showAlcoholic: Boolean) = drinksRepository.getDrinksByAlcoholic(showAlcoholic)
    suspend fun getDrinkById(id: String) = drinksRepository.getDrinkById(id)
    fun getDrinkByNameFlow(name: String) = drinksRepository.getDrinkByNameFlow(name)
}