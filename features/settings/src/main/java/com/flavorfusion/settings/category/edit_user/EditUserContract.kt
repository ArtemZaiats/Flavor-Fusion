package com.flavorfusion.settings.category.edit_user

import com.flavorfusion.core_ui.mvi.MviConfig
import com.flavorfusion.core_ui.mvi.UiAction
import com.flavorfusion.core_ui.mvi.UiEffect
import com.flavorfusion.core_ui.mvi.UiEvent
import com.flavorfusion.core_ui.mvi.UiState
import javax.inject.Inject

interface EditUserContract {

    class Config @Inject constructor() : MviConfig<State> {
        override fun initialState() = State()
        override fun reducer() = EditUserReducer()
    }

    data class State(
        val email: String = "",
        val initialFirstName: String = "",
        val initialLastName: String = "",
        val initialAvatarUrl: String = "",
        val firstName: String = "",
        val lastName: String = "",
        val pickedImageUri: String? = null,
        val isSaving: Boolean = false
    ) : UiState {
        val hasChanges: Boolean
            get() = firstName != initialFirstName ||
                lastName != initialLastName ||
                pickedImageUri != null
    }

    sealed interface Event : UiEvent {
        data class OnFirstNameChanged(val value: String) : Event
        data class OnLastNameChanged(val value: String) : Event
        data class OnImagePicked(val uri: String?) : Event
        data object OnSaveClicked : Event
        data object OnBackClicked : Event
    }

    sealed interface Action : UiAction {
        data class SeedProfile(
            val email: String,
            val firstName: String,
            val lastName: String,
            val avatarUrl: String
        ) : Action

        data class UpdateFirstName(val value: String) : Action
        data class UpdateLastName(val value: String) : Action
        data class UpdatePickedImage(val uri: String?) : Action
        data class ShowSaving(val show: Boolean) : Action
    }

    sealed interface Effect : UiEffect {
        data object NavigateBack : Effect
    }
}
