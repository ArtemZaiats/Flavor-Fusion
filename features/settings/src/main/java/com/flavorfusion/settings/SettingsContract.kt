package com.flavorfusion.settings

import com.flavorfusion.core_ui.mvi.MviConfig
import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction
import com.flavorfusion.core_ui.mvi.UiEffect
import com.flavorfusion.core_ui.mvi.UiEvent
import com.flavorfusion.core_ui.mvi.UiState
import com.flavorfusion.settings.model.SettingsCategory
import javax.inject.Inject

interface SettingsContract {
    class Config @Inject constructor() : MviConfig<State> {
        override fun initialState() = State()
        override fun reducer() = SettingsReducer()
    }

    data class State(
        val categories: List<SettingsCategory> = emptyList()
    ): UiState

    sealed interface Event : UiEvent {
        data class OnItemClicked(val id: Int) : Event
        data object OnLogOutClicked : Event
    }

    sealed interface Action : UiAction {
        data class UpdateCategories(val categories: List<SettingsCategory>) : Action
    }

    sealed interface Effect : UiEffect {
        data object NavigateToAppTheme : Effect
        data object ShowLogOutDialog : Effect
    }
}