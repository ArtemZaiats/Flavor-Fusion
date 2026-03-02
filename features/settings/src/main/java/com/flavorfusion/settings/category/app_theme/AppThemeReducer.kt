package com.flavorfusion.settings.category.app_theme

import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction

class AppThemeReducer : Reducer<AppThemeContract.State> {
    override fun AppThemeContract.State.reduce(action: UiAction): AppThemeContract.State {
        val action = (action as? AppThemeContract.Action) ?: return this

        return when (action) {
            is AppThemeContract.Action.UpdateThemes -> copy(themes = action.themes)
        }
    }
}