package com.flavorfusion.common_ui.error

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Singleton

interface ErrorMessageProvider : DialogActionProvider {
    val errorFlow: Flow<ErrorMessage>
    suspend fun sendError(errorMessage: ErrorMessage, tag: String)
}

@Singleton
class DefaultErrorMessageProvider : ErrorMessageProvider {
    private val _errorFlow: Channel<ErrorMessage> =
        Channel(Channel.BUFFERED, BufferOverflow.DROP_OLDEST)
    override val errorFlow: Flow<ErrorMessage> get() = _errorFlow.receiveAsFlow()

    private val _dialogActionFlow = MutableSharedFlow<String>()
    override val dialogActionFlow: SharedFlow<String> = _dialogActionFlow.asSharedFlow()

    private var tag: String = ""

    override suspend fun sendError(
        errorMessage: ErrorMessage,
        tag: String
    ) {
        this.tag = tag
        _errorFlow.send(errorMessage)
    }

    override suspend fun notifyDialogAction() {
        _dialogActionFlow.emit(tag)
    }
}