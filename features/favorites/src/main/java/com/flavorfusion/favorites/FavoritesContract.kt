package com.flavorfusion.favorites

import com.flavorfusion.common_ui.model.favorites.FavoriteItemUi
import com.flavorfusion.core_ui.mvi.MviConfig
import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction
import com.flavorfusion.core_ui.mvi.UiEffect
import com.flavorfusion.core_ui.mvi.UiEvent
import com.flavorfusion.core_ui.mvi.UiState
import javax.inject.Inject

interface FavoritesContract {

    class Config @Inject constructor() : MviConfig<State> {
        override fun initialState(): State = State()
        override fun reducer(): Reducer<State> = FavoritesReducer()
    }

    data class State(
        val favorites: List<FavoriteItemUi> = emptyList(),
        val loading: Boolean = true
    ) : UiState {
        val isEmpty: Boolean get() = !loading && favorites.isEmpty()
    }

    sealed interface Event : UiEvent {
        data class OnFavoriteToggled(val item: FavoriteItemUi) : Event
    }

    sealed interface Action : UiAction {
        data class UpdateFavorites(val favorites: List<FavoriteItemUi>) : Action
        data class Loading(val show: Boolean) : Action
    }

    sealed interface Effect : UiEffect
}
