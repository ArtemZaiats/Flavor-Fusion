package com.flavorfusion.settings.category.app_theme

import com.flavorfusion.common_ui.model.AppThemeUi
import com.flavorfusion.core_ui.mvi.MviConfig
import com.flavorfusion.core_ui.mvi.UiAction
import com.flavorfusion.core_ui.mvi.UiEffect
import com.flavorfusion.core_ui.mvi.UiEvent
import com.flavorfusion.core_ui.mvi.UiState
import javax.inject.Inject

interface AppThemeContract {
    class Config @Inject constructor() : MviConfig<State> {
        override fun initialState() = State()
        override fun reducer() = AppThemeReducer()
    }

    data class State(
        val themes: List<AppThemeUi> = emptyList()
    ) : UiState

    sealed interface Event : UiEvent {
        data class OnThemeSelected(val theme: AppThemeUi) : Event
        data object OnBackClicked : Event
    }

    sealed interface Effect : UiEffect {
        data object NavigateBack : Effect
    }

    sealed interface Action : UiAction {
        data class UpdateThemes(val themes: List<AppThemeUi>) : Action
    }
}