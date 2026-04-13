package com.flavorfusion.favorites

import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction

class FavoritesReducer : Reducer<FavoritesContract.State> {
    override fun FavoritesContract.State.reduce(action: UiAction): FavoritesContract.State {
        val action = (action as? FavoritesContract.Action) ?: return this

        return when (action) {
            is FavoritesContract.Action.UpdateFavorites -> copy(favorites = action.favorites)
            is FavoritesContract.Action.Loading -> copy(loading = action.show)
        }
    }
}
