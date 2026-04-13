package com.flavorfusion.drinks

import com.flavorfusion.common_ui.error.ErrorMessage
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
        val loading: Boolean = false,
        val refreshLoading: Boolean = false,
        val errorMessage: ErrorMessage? = null,
        val drinks: List<DrinkUi> = emptyList(),
        val searchValue: String = "",
        val showSearch: Boolean = false,
        val searchDrinks: List<DrinkUi> = emptyList()
    ) : UiState {
        val hasProgress: Boolean
            get() = loading || refreshLoading
    }

    sealed interface Event : UiEvent {
        data class OnSearchValueChanged(val value: String) : Event
        data class OnDrinkClicked(val drink: DrinkUi) : Event
        data object OnSearchClicked : Event
        data object OnSearchCloseClicked : Event
        data object OnRetryClicked : Event
        data object OnRefresh : Event
        data class OnFavoriteToggled(val drink: DrinkUi) : Event
    }

    sealed interface Action : UiAction {
        data class Loading(val show: Boolean, val errorMessage: ErrorMessage?) : Action
        data class RefreshLoading(val showRefreshing: Boolean) : Action
        data class UpdateDrinks(val drinks: List<DrinkUi>) : Action
        data class UpdateSearchDrinks(val searchDrinks: List<DrinkUi>) : Action
        data class UpdateSearchValue(val searchValue: String) : Action
        data class UpdateShowSearch(val showSearch: Boolean) : Action
        data class UpdateFavoriteIds(val favoriteIds: Set<String>) : Action
        data object HideAllLoadings : Action
    }

    sealed interface Effect : UiEffect {
        data class NavigateToDrinkDetails(val drinkId: String) : Effect
    }
}