package com.flavorfusion.flavorfusion.main

import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction

class MainReducer : Reducer<MainContract.State> {
    override fun MainContract.State.reduce(action: UiAction): MainContract.State {
        val action = action as? MainContract.Action ?: return this

        return when (action) {
            is MainContract.Action.UpdateAppTheme -> copy(appTheme = action.appTheme)
            is MainContract.Action.UpdateErrorMessage -> copy(errorMessage = action.errorMessage)
        }
    }
}