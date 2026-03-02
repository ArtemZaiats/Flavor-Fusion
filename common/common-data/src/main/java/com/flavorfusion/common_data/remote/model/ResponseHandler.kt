package com.flavorfusion.common_data.remote.model

import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_domain.model.error.DataError

interface ResponseHandler {
    suspend fun <T, R> handleResponse(response: T, mapper: suspend (T?) -> R): Result<R>
}

class DefaultResponseHandler : ResponseHandler {
    override suspend fun <T, R> handleResponse(response: T, mapper: suspend (T?) -> R): Result<R> {
        return if (response != null) {
            Result.Success(mapper(response))
        } else {
            Result.Error(DataError.Network.ServerError)
        }
    }
}