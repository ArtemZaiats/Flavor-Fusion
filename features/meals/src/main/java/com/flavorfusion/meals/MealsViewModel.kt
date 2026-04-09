package com.flavorfusion.meals

import com.flavorfusion.common_domain.interactors.MealsInteractor
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.common_ui.model.meal.toUi
import com.flavorfusion.core_ui.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val mealsInteractor: MealsInteractor,
    private val executor: Executor,
    config: MealsContract.Config
) : MviViewModel<MealsContract.State, MealsContract.Event>(config), Executor by executor {

    init {
        getMeals()
    }

    override fun handleEvent(event: MealsContract.Event) {
        when (event) {
            MealsContract.Event.OnRefresh -> getMeals(isInitial = false, isRefreshing = true)
            MealsContract.Event.OnRetryClicked -> getMeals()
        }
    }

    private fun getMeals(isInitial: Boolean = true, isRefreshing: Boolean = false) {
        launch(
            action = {
                dispatch(MealsContract.Action.Loading(show = isInitial, errorMessage = currentState.errorMessage))
                dispatch(MealsContract.Action.RefreshLoading(show = isRefreshing))
                mealsInteractor.getMealsByCategory("Seafood")
            },
            onSuccess = {
                dispatch(
                    MealsContract.Action.UpdateMeals(
                        meals = it?.toUi()?.sortedBy { it.mealName } ?: emptyList()
                    )
                )
                dispatch(MealsContract.Action.HideAllLoadings)
            },
            onError = { errorMessage ->
                if (currentState.meals.isEmpty()) {
                    dispatch(MealsContract.Action.Loading(show = false, errorMessage = errorMessage))
                } else {
                    dispatch(MealsContract.Action.HideAllLoadings)
                }
            }
        )
    }
}
