package com.flavorfusion.common_data.remote.retrofit_factory.interceptors

import android.content.Context
import com.flavorfusion.core_data.NetworkState
import com.flavorfusion.core_data.NetworkUtils
import com.flavorfusion.core_data.model.NoInternetException
import com.flavorfusion.core_data.model.NoPingException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        checkInternetConnection()

        return chain.proceed(chain.request().newBuilder().build())
    }

    private fun checkInternetConnection() {
        when(NetworkUtils.checkNetworkState(context)) {
            NetworkState.NOT_CONNECT -> throw NoInternetException()
            NetworkState.NOT_PING -> throw NoPingException()
            NetworkState.AVAILABLE -> {}
        }
    }

}