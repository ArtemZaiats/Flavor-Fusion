package com.flavorfusion.flavorfusion.main

import com.flavorfusion.common_ui.error.ErrorMessage
import com.flavorfusion.common_ui.model.AppThemeUi
import com.flavorfusion.core_ui.mvi.MviConfig
import com.flavorfusion.core_ui.mvi.UiAction
import com.flavorfusion.core_ui.mvi.UiEffect
import com.flavorfusion.core_ui.mvi.UiEvent
import com.flavorfusion.core_ui.mvi.UiState
import javax.inject.Inject

interface MainContract {

    class Config @Inject constructor() : MviConfig<State> {
        override fun initialState() = State()
        override fun reducer() = MainReducer()
    }

    data class State(
        val appTheme: AppThemeUi = AppThemeUi(),
        val errorMessage: ErrorMessage? = null,
        val isAuthenticated: Boolean = false,
        val authStateLoaded: Boolean = false
    ) : UiState

    sealed interface Event : UiEvent {
        data object OnDialogActionClicked : Event
    }

    sealed interface Action : UiAction {
        data class UpdateAppTheme(val appTheme: AppThemeUi) : Action
        data class UpdateErrorMessage(val errorMessage: ErrorMessage) : Action
        data class UpdateAuthState(val isAuthenticated: Boolean) : Action
    }

    sealed interface Effect : UiEffect {
        data object ShowErrorDialog : Effect
    }
}