package com.flavorfusion.drinks.drinkDetails

import com.flavorfusion.common_ui.model.drink.DrinkDetailsUi
import com.flavorfusion.core_ui.mvi.MviConfig
import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction
import com.flavorfusion.core_ui.mvi.UiEffect
import com.flavorfusion.core_ui.mvi.UiEvent
import com.flavorfusion.core_ui.mvi.UiState
import javax.inject.Inject

interface DrinkDetailsContract {
    class Config @Inject constructor() : MviConfig<State> {
        override fun initialState() = State()
        override fun reducer() = DrinkDetailsReducer()
    }

    data class State(
        val loading: Boolean = false,
        val drink: DrinkDetailsUi = DrinkDetailsUi()
    ) : UiState

    sealed interface Effect : UiEffect {
        object NavigateBack : Effect
    }

    sealed interface Event : UiEvent {
        data class OnDrinkIdReceived(val drinkId: String) : Event
        data object OnIconBackClicked : Event
    }

    sealed interface Action : UiAction {
        data class UpdateDrink(val drink: DrinkDetailsUi) : Action
        data class UpdateLoading(val loading: Boolean) : Action
    }
}