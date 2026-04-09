package com.flavorfusion.meals.mealDetails

import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction

class MealDetailsReducer : Reducer<MealDetailsContract.State> {
    override fun MealDetailsContract.State.reduce(action: UiAction): MealDetailsContract.State {
        return when (val action = action as MealDetailsContract.Action) {
            is MealDetailsContract.Action.UpdateMeal -> copy(meal = action.meal)
            is MealDetailsContract.Action.UpdateLoading -> copy(loading = action.loading)
        }
    }
}
