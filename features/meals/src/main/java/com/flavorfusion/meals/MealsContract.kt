package com.flavorfusion.meals

import com.flavorfusion.common_ui.error.ErrorMessage
import com.flavorfusion.common_ui.model.meal.MealUi
import com.flavorfusion.core_ui.mvi.MviConfig
import com.flavorfusion.core_ui.mvi.Reducer
import com.flavorfusion.core_ui.mvi.UiAction
import com.flavorfusion.core_ui.mvi.UiEffect
import com.flavorfusion.core_ui.mvi.UiEvent
import com.flavorfusion.core_ui.mvi.UiState
import javax.inject.Inject

interface MealsContract {
    class Config @Inject constructor() : MviConfig<State> {
        override fun initialState(): State = State()
        override fun reducer(): Reducer<State> = MealsReducer()
    }

    data class State(
        val loading: Boolean = false,
        val refreshLoading: Boolean = false,
        val errorMessage: ErrorMessage? = null,
        val meals: List<MealUi> = emptyList()
    ) : UiState {
        val hasProgress: Boolean
            get() = loading || refreshLoading
    }

    sealed interface Event : UiEvent {
        data object OnRefresh : Event
        data object OnRetryClicked : Event
        data class OnMealClicked(val meal: MealUi) : Event
    }

    sealed interface Action : UiAction {
        data class Loading(val show: Boolean, val errorMessage: ErrorMessage?) : Action
        data class RefreshLoading(val show: Boolean) : Action
        data class UpdateMeals(val meals: List<MealUi>) : Action
        data object HideAllLoadings : Action
    }

    sealed interface Effect : UiEffect {
        data class NavigateToMealDetails(val mealId: String) : Effect
    }
}
