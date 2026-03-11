package com.flavorfusion.common_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_domain.model.onError
import com.flavorfusion.common_domain.model.onSuccess
import com.flavorfusion.common_ui.error.ErrorMessage
import com.flavorfusion.common_ui.error.ErrorMessageExtractor
import com.flavorfusion.common_ui.error.ErrorMessageProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface Executor {
    fun <T : ViewModel, D> T.launch(
        tag: String = "TAG",
        context: CoroutineContext = Dispatchers.IO,
        handleActionError: Boolean = true,
        onDialogAction: (String) -> Unit = {},
        onSuccess: suspend (D) -> Unit = {},
        onError: (ErrorMessage) -> Unit = {},
        action: suspend CoroutineScope.() -> Result<D>
    ): Job
}

class DefaultExecutor @Inject constructor(
    private val errorMessageExtractor: ErrorMessageExtractor,
    private val errorMessageProvider: ErrorMessageProvider
) : Executor {

    private var dialogActionJob: Job? = null

    override fun <T : ViewModel, D> T.launch(
        tag: String,
        context: CoroutineContext,
        handleActionError: Boolean,
        onDialogAction: (String) -> Unit,
        onSuccess: suspend (D) -> Unit,
        onError: (ErrorMessage) -> Unit,
        action: suspend CoroutineScope.() -> Result<D>
    ): Job {
        return viewModelScope.launch(context) {
            action.invoke(this)
                .onSuccess { onSuccess.invoke(it) }
                .onError { rootError ->
                    val errorMessage = errorMessageExtractor.extract(rootError)
                    if (handleActionError) {
                        subscribeToDialogAction(onDialogAction)
                        errorMessageProvider.sendError(errorMessage, tag)
                    }
                    onError.invoke(errorMessage)
                }
        }
    }

    private fun <T : ViewModel> T.subscribeToDialogAction(onDialogAction: (String) -> Unit) {
        if (dialogActionJob?.isActive == true) {
            dialogActionJob?.cancel()
        }
        dialogActionJob = errorMessageProvider.dialogActionFlow
            .onEach {
                onDialogAction(it)
            }
            .launchIn(viewModelScope)
    }
}