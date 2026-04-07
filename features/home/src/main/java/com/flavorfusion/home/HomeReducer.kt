package com.flavorfusion.home

import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction

class HomeReducer : Reducer<HomeContract.State> {
    override fun HomeContract.State.reduce(action: UiAction): HomeContract.State {
        val action = action as? HomeContract.Action ?: return this

        return when (action) {
            is HomeContract.Action.LoadFacts -> copy(facts = action.facts)
        }
    }
}
