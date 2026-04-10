package com.flavorfusion.common_data.repositories

import com.flavorfusion.common_data.di.qualifiers.MealsClient
import com.flavorfusion.common_data.remote.model.ResponseHandler
import com.flavorfusion.common_data.remote.model.error.asDataError
import com.flavorfusion.common_data.remote.model.meals.toDomain
import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_data.remote.services.MealsApiService
import com.flavorfusion.common_domain.model.meals.Meal
import com.flavorfusion.common_domain.model.meals.MealDetails
import com.flavorfusion.common_domain.repositories.MealsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsRepositoryImpl @Inject constructor(
    @param:MealsClient
    private val service: MealsApiService,
    private val responseHandler: ResponseHandler
) : MealsRepository {

    override suspend fun getMealsByCategory(category: String): Result<List<Meal>?> {
        return service.getMealsByCategory(category).fold(
            onSuccess = { response ->
                responseHandler.handleResponse(response) { it?.toDomain() }
            },
            onFailure = { Result.Error(it.asDataError()) }
        )
    }

    override suspend fun getMealById(id: String): Result<List<MealDetails>?> {
        return service.getMealById(id).fold(
            onSuccess = { response ->
                responseHandler.handleResponse(response) { it?.toDomain() }
            },
            onFailure = { Result.Error(it.asDataError()) }
        )
    }

    override fun getMealByNameFlow(name: String): Flow<Result<List<Meal>?>> = flow {
        val response = service.getMealsByName(name)
        emit(
            response.fold(
                onSuccess = { responseHandler.handleResponse(it) { it?.toDomain() } },
                onFailure = { Result.Error(it.asDataError()) }
            )
        )
    }.flowOn(Dispatchers.IO)
}