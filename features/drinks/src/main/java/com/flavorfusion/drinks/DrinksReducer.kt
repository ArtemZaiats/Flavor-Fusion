package com.flavorfusion.drinks

import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction

class DrinksReducer : Reducer<DrinksContract.State> {
    override fun DrinksContract.State.reduce(action: UiAction): DrinksContract.State {
        val action = (action as? DrinksContract.Action) ?: return this

        return when (action) {
            is DrinksContract.Action.Loading -> copy(
                loading = action.show,
                errorMessage = action.errorMessage
            )
            is DrinksContract.Action.RefreshLoading -> copy(refreshLoading = action.showRefreshing)
            is DrinksContract.Action.UpdateDrinks -> copy(
                drinks = action.drinks.map { it.copy(isFavorite = it.drinkId in favoriteIds) }
            )
            is DrinksContract.Action.UpdateSearchDrinks -> copy(
                searchDrinks = action.searchDrinks.map { it.copy(isFavorite = it.drinkId in favoriteIds) }
            )
            is DrinksContract.Action.UpdateSearchValue -> copy(searchValue = action.searchValue)
            is DrinksContract.Action.UpdateShowSearch -> copy(showSearch = action.showSearch)
            is DrinksContract.Action.UpdateFavoriteIds -> copy(
                favoriteIds = action.favoriteIds,
                drinks = drinks.map { it.copy(isFavorite = it.drinkId in action.favoriteIds) },
                searchDrinks = searchDrinks.map { it.copy(isFavorite = it.drinkId in action.favoriteIds) }
            )
            DrinksContract.Action.HideAllLoadings -> copy(
                loading = false,
                refreshLoading = false,
                errorMessage = null
            )
        }
    }
}