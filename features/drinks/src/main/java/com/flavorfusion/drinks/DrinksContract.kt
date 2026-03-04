package com.flavorfusion.drinks

import com.flavorfusion.common_ui.model.drink.DrinkUi
import com.flavorfusion.core_ui.mvi.MviConfig
import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction
import com.flavorfusion.core_ui.mvi.UiEffect
import com.flavorfusion.core_ui.mvi.UiEvent
import com.flavorfusion.core_ui.mvi.UiState
import javax.inject.Inject

interface DrinksContract {
    class Config @Inject constructor() : MviConfig<State> {
        override fun initialState(): State = State()
        override fun reducer(): Reducer<State> = DrinksReducer()
    }

    data class State(
        val progress: Boolean = false,
        val refreshProgress: Boolean = false,
        val drinks: List<DrinkUi> = emptyList(),
        val searchValue: String = "",
        val showSearch: Boolean = false,
        val searchDrinks: List<DrinkUi> = emptyList()
    ) : UiState

    sealed interface Event : UiEvent {
        data class OnSearchValueChanged(val value: String) : Event
        data class OnDrinkClicked(val drink: DrinkUi) : Event
        data object OnSearchClicked : Event
        data object OnSearchCloseClicked : Event
        data object OnRefresh : Event
    }

    sealed interface Action : UiAction {
        data class Progress(val show: Boolean) : Action
        data class RefreshProgress(val showRefreshing: Boolean) : Action
        data class UpdateDrinks(val drinks: List<DrinkUi>) : Action
        data class UpdateSearchDrinks(val searchDrinks: List<DrinkUi>) : Action
        data class UpdateSearchValue(val searchValue: String) : Action
        data class UpdateShowSearch(val showSearch: Boolean) : Action
        data object HideAllProgress : Action
    }

    sealed interface Effect : UiEffect {
        data class NavigateToDrinkDetails(val drinkId: String) : Effect
    }
}