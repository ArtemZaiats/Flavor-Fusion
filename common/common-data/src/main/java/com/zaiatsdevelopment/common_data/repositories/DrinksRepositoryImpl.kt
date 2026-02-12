package com.zaiatsdevelopment.common_data.repositories

import com.flavorfusion.common_domain.model.drinks.Drink
import com.flavorfusion.common_domain.model.drinks.DrinkDetails
import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_domain.repositories.DrinksRepository
import com.zaiatsdevelopment.common_data.di.qualifiers.DrinksClient
import com.zaiatsdevelopment.common_data.remote.model.ResponseHandler
import com.zaiatsdevelopment.common_data.remote.model.drinks.toDomain
import com.zaiatsdevelopment.common_data.remote.model.error.asDataError
import com.zaiatsdevelopment.common_data.remote.services.DrinksApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DrinksRepositoryImpl @Inject constructor(
    @DrinksClient
    private val service: DrinksApiService,
    private val responseHandler: ResponseHandler
) : DrinksRepository {

    override suspend fun getDrinksByAlcoholic(alcoholic: String): Result<List<Drink>?> {
        return service.getDrinksByAlcoholic(alcoholic).fold(
            onSuccess = { response ->
                responseHandler.handleResponse(response) {
                    it?.toDomain()
                }
            },
            onFailure = { Result.Error(it.asDataError()) }
        )
    }

    override suspend fun getDrinkById(id: String): Result<List<DrinkDetails>?> {
        return service.getDrinkById(id).fold(
            onSuccess = { response ->
                responseHandler.handleResponse(response) {
                    it?.toDomain()
                }
            },
            onFailure = { Result.Error(it.asDataError()) }
        )
    }

    override suspend fun getDrinkByName(name: String): Result<List<Drink>?> {
        return service.getDrinksByName(name).fold(
            onSuccess = { response ->
                responseHandler.handleResponse(response) {
                    it?.toDomain()
                }
            },
            onFailure = { Result.Error(it.asDataError()) }
        )
    }
}