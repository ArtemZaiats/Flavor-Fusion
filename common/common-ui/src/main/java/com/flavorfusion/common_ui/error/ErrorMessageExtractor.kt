package com.flavorfusion.common_ui.error

import android.content.Context
import android.content.res.loader.ResourcesProvider
import com.flavorfusion.common_domain.model.RootError
import com.flavorfusion.common_domain.model.error.DataError
import com.flavorfusion.common_ui.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface ErrorMessageExtractor {
    fun extract(rootError: RootError): ErrorMessage
}

class DefaultMessageExtractor @Inject constructor(
    @param:ApplicationContext private val context: Context
) : ErrorMessageExtractor {

    private val defaultErrorMessage: ErrorMessage
        get() = ErrorMessage(
            title = context.getString(R.string.backend_error_title),
            errorText = context.getString(R.string.backend_error_internal_error),
            buttonText = context.getString(R.string.backend_error_dismiss)
        )

    private val noInternetErrorMessage: ErrorMessage
        get() = ErrorMessage(
            title = context.getString(R.string.network_error_title),
            errorText = context.getString(R.string.network_error_message),
            buttonText = context.getString(R.string.network_error_dismiss)
        )

    private val noPingErrorMessage: ErrorMessage
        get() = ErrorMessage(
            title = context.getString(R.string.internet_no_ping_error_title),
            errorText = context.getString(R.string.internet_no_ping_error_message),
            buttonText = context.getString(R.string.network_error_dismiss)
        )

    override fun extract(rootError: RootError): ErrorMessage {
        return when (rootError) {
            DataError.Network.RequestTimeout -> defaultErrorMessage.copy(
                errorText = context.getString(
                    R.string.mock_timeout_error
                )
            )

            DataError.Network.NoInternet -> noInternetErrorMessage
            DataError.Network.NoPing -> noPingErrorMessage
            DataError.Network.ServerError -> defaultErrorMessage.copy(
                errorText = context.getString(
                    R.string.mock_server_error
                )
            )
            else -> defaultErrorMessage
        }
    }

}