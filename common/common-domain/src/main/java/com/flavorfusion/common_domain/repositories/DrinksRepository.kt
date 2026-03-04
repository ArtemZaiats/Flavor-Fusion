package com.flavorfusion.common_domain.repositories

import com.flavorfusion.common_domain.model.drinks.Drink
import com.flavorfusion.common_domain.model.drinks.DrinkDetails
import com.flavorfusion.common_domain.model.Result
import kotlinx.coroutines.flow.Flow

interface DrinksRepository {
    suspend fun getDrinksByAlcoholic(showAlcoholic: Boolean): Result<List<Drink>?>
    suspend fun getDrinkById(id: String): Result<List<DrinkDetails>?>
    fun getDrinkByNameFlow(name: String): Flow<Result<List<Drink>?>>
}