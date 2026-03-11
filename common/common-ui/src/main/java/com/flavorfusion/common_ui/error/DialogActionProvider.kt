package com.flavorfusion.common_ui.error

import kotlinx.coroutines.flow.SharedFlow

interface DialogActionProvider {
    val dialogActionFlow: SharedFlow<String>
    suspend fun notifyDialogAction()
}