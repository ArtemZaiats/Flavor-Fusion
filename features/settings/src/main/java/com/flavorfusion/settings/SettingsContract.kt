package com.flavorfusion.settings

import com.flavorfusion.common_ui.model.profile.ProfileUi
import com.flavorfusion.core_ui.mvi.MviConfig
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
        val profile: ProfileUi = ProfileUi(
            email = "john.adams@example-pet-store.com",
            firstName = "John",
            lastName = "Doe"
        ),
        val categories: List<SettingsCategory> = emptyList()
    ) : UiState

    sealed interface Event : UiEvent {
        data class OnItemClicked(val id: Int) : Event
        data object OnLogOutClicked : Event
    }

    sealed interface Action : UiAction {
        data class UpdateCategories(val categories: List<SettingsCategory>) : Action
        data class UpdateProfile(val profile: ProfileUi) : Action
    }

    sealed interface Effect : UiEffect {
        data object NavigateToAppTheme : Effect
        data object ShowLogOutDialog : Effect
    }
}