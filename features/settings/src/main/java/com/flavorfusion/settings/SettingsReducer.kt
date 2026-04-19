package com.flavorfusion.settings

import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction

class SettingsReducer : Reducer<SettingsContract.State>{
    override fun SettingsContract.State.reduce(action: UiAction): SettingsContract.State {
        val action = action as SettingsContract.Action

        return when(action) {
            is SettingsContract.Action.UpdateCategories -> copy(categories = action.categories)
            is SettingsContract.Action.UpdateProfile -> copy(profile = action.profile)
        }
    }
}