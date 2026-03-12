package com.flavorfusion.drinks.drinkDetails

import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction

class DrinkDetailsReducer : Reducer<DrinkDetailsContract.State> {
    override fun DrinkDetailsContract.State.reduce(action: UiAction): DrinkDetailsContract.State {
        return when (val action = action as DrinkDetailsContract.Action) {
            is DrinkDetailsContract.Action.UpdateDrink -> copy(drink = action.drink)
            is DrinkDetailsContract.Action.UpdateLoading -> copy(loading = action.loading)
        }
    }
}