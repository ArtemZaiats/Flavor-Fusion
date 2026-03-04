package com.flavorfusion.common_data.repositories

import com.flavorfusion.common_domain.model.drinks.Drink
import com.flavorfusion.common_domain.model.drinks.DrinkDetails
import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_domain.repositories.DrinksRepository
import com.flavorfusion.common_data.di.qualifiers.DrinksClient
import com.flavorfusion.common_data.remote.model.ResponseHandler
import com.flavorfusion.common_data.remote.model.drinks.toDomain
import com.flavorfusion.common_data.remote.model.error.asDataError
import com.flavorfusion.common_data.remote.services.DrinksApiService
import com.flavorfusion.common_domain.model.combineResults
import com.flavorfusion.common_domain.model.drinks.DrinkAlcoholicType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.fold

@Singleton
class DrinksRepositoryImpl @Inject constructor(
    @param:DrinksClient
    private val service: DrinksApiService,
    private val responseHandler: ResponseHandler
) : DrinksRepository {

    override suspend fun getDrinksByAlcoholic(showAlcoholic: Boolean): Result<List<Drink>?> {
        return coroutineScope {

            suspend fun request(type: DrinkAlcoholicType): Result<List<Drink>?> {
                return service.getDrinksByAlcoholic(type.type).fold(
                    onSuccess = { response ->
                        responseHandler.handleResponse(response) { it?.toDomain() }
                    },
                    onFailure = { Result.Error(it.asDataError()) }
                )
            }

            if (!showAlcoholic) {
                return@coroutineScope request(DrinkAlcoholicType.NON_ALCOHOLIC)
            }

            val nonAlcoholicDeferred = async { request(DrinkAlcoholicType.NON_ALCOHOLIC) }
            val alcoholicDeferred = async { request(DrinkAlcoholicType.ALCOHOLIC) }
            val optionalDeferred = async { request(DrinkAlcoholicType.OPTIONAL) }

            combineResults(
                nonAlcoholicDeferred.await(),
                alcoholicDeferred.await(),
                optionalDeferred.await()
            )
        }
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

    override fun getDrinkByNameFlow(name: String): Flow<Result<List<Drink>?>> = flow {
        val response = service.getDrinksByName(name)
        emit(
            response.fold(
                onSuccess = { responseHandler.handleResponse(it) { it?.toDomain() } },
                onFailure = { Result.Error(it.asDataError()) }
            )
        )
    }.flowOn(Dispatchers.IO)
}