package com.flavorfusion.meals

import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction

class MealsReducer : Reducer<MealsContract.State> {
    override fun MealsContract.State.reduce(action: UiAction): MealsContract.State {
        val action = (action as? MealsContract.Action) ?: return this

        return when (action) {
            is MealsContract.Action.Loading -> copy(
                loading = action.show,
                errorMessage = action.errorMessage
            )
            is MealsContract.Action.RefreshLoading -> copy(refreshLoading = action.show)
            is MealsContract.Action.UpdateMeals -> copy(meals = action.meals)
            is MealsContract.Action.UpdateSearchMeals -> copy(searchMeals = action.searchMeals)
            is MealsContract.Action.UpdateSearchValue -> copy(searchValue = action.searchValue)
            is MealsContract.Action.UpdateShowSearch -> copy(showSearch = action.showSearch)
            MealsContract.Action.HideAllLoadings -> copy(
                loading = false,
                refreshLoading = false,
                errorMessage = null
            )
        }
    }
}
