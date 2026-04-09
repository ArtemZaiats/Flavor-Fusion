package com.flavorfusion.meals.mealDetails

import com.flavorfusion.common_ui.model.meal.MealDetailsUi
import com.flavorfusion.core_ui.mvi.MviConfig
import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction
import com.flavorfusion.core_ui.mvi.UiEffect
import com.flavorfusion.core_ui.mvi.UiEvent
import com.flavorfusion.core_ui.mvi.UiState
import javax.inject.Inject

interface MealDetailsContract {
    class Config @Inject constructor() : MviConfig<State> {
        override fun initialState() = State()
        override fun reducer() = MealDetailsReducer()
    }

    data class State(
        val loading: Boolean = false,
        val meal: MealDetailsUi = MealDetailsUi()
    ) : UiState

    sealed interface Effect : UiEffect {
        data object NavigateBack : Effect
    }

    sealed interface Event : UiEvent {
        data class OnMealIdReceived(val mealId: String) : Event
        data object OnIconBackClicked : Event
    }

    sealed interface Action : UiAction {
        data class UpdateMeal(val meal: MealDetailsUi) : Action
        data class UpdateLoading(val loading: Boolean) : Action
    }
}
