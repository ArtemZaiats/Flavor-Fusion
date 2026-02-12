package com.flavorfusion.core_ui.mvi

sealed interface CommonEffect : UiEffect {
    data class Toast(val message: String) : CommonEffect
    data class CopyToClipboard(val text: String) : CommonEffect
    data class OpenUrl(val url: String) : CommonEffect
    data object HideKeyboard : CommonEffect
}