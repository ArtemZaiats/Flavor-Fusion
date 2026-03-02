package com.flavorfusion.flavorfusion.main

import com.flavorfusion.common_ui.model.AppThemeUi
import com.flavorfusion.core_ui.mvi.MviConfig
import com.flavorfusion.core_ui.mvi.Reducer
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
        val appTheme: AppThemeUi = AppThemeUi()
    ) : UiState

    sealed interface Event : UiEvent {
        data object OnDialogActionClicked : Event
    }

    sealed interface Action : UiAction {
        data class UpdateAppTheme(val appTheme: AppThemeUi) : Action
    }

    sealed interface Effect : UiEffect {
        data object ShowErrorDialog : Effect
    }
}