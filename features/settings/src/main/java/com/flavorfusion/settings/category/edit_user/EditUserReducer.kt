package com.flavorfusion.settings.category.edit_user

import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction

class EditUserReducer : Reducer<EditUserContract.State> {
    override fun EditUserContract.State.reduce(action: UiAction): EditUserContract.State {
        val action = (action as? EditUserContract.Action) ?: return this

        return when (action) {
            is EditUserContract.Action.SeedProfile -> copy(
                email = action.email,
                initialFirstName = action.firstName,
                initialLastName = action.lastName,
                initialAvatarUrl = action.avatarUrl,
                firstName = action.firstName,
                lastName = action.lastName
            )
            is EditUserContract.Action.UpdateFirstName -> copy(firstName = action.value)
            is EditUserContract.Action.UpdateLastName -> copy(lastName = action.value)
            is EditUserContract.Action.UpdatePickedImage -> copy(pickedImageUri = action.uri)
            is EditUserContract.Action.ShowSaving -> copy(isSaving = action.show)
        }
    }
}
