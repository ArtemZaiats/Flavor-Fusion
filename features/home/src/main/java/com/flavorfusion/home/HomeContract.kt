package com.flavorfusion.home

import com.flavorfusion.common_ui.model.FunFactUi
import com.flavorfusion.core_ui.mvi.MviConfig
import com.flavorfusion.core_ui.mvi.UiAction
import com.flavorfusion.core_ui.mvi.UiEffect
import com.flavorfusion.core_ui.mvi.UiEvent
import com.flavorfusion.core_ui.mvi.UiState
import javax.inject.Inject

interface HomeContract {

    class Config @Inject constructor() : MviConfig<State> {
        override fun initialState() = State()
        override fun reducer() = HomeReducer()
    }

    data class State(
        val facts: List<FunFactUi> = emptyList()
    ) : UiState

    sealed interface Event : UiEvent

    sealed interface Action : UiAction {
        data class LoadFacts(val facts: List<FunFactUi>) : Action
    }
}
