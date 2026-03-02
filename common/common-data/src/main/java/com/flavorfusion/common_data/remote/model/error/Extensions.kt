package com.flavorfusion.common_data.remote.model.error

import com.flavorfusion.common_domain.model.error.DataError
import com.flavorfusion.core_data.model.NoInternetException
import com.flavorfusion.core_data.model.NoPingException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.asDataError(): DataError {
    return when (this) {
        is NoInternetException, is UnknownHostException -> DataError.Network.NoInternet
        is NoPingException -> DataError.Network.NoPing
        is HttpException -> handleHttpException(this)
        is SocketTimeoutException -> DataError.Network.RequestTimeout
        is IOException -> DataError.Local.IOError
        else -> DataError.Unknown
    }
}

// TODO: add http codes
private fun handleHttpException(httpException: HttpException): DataError {
    return DataError.Network.ServerError
}