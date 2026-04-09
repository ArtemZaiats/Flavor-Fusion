package com.flavorfusion.meals.mealDetails

import com.flavorfusion.common_domain.interactors.MealsInteractor
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.common_ui.model.meal.MealDetailsUi
import com.flavorfusion.common_ui.model.meal.toUi
import com.flavorfusion.core_ui.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val mealsInteractor: MealsInteractor,
    private val executor: Executor,
    config: MealDetailsContract.Config
) : MviViewModel<MealDetailsContract.State, MealDetailsContract.Event>(config),
    Executor by executor {

    override fun handleEvent(event: MealDetailsContract.Event) {
        when (event) {
            is MealDetailsContract.Event.OnMealIdReceived -> loadMeal(event.mealId)
            is MealDetailsContract.Event.OnIconBackClicked ->
                publish { MealDetailsContract.Effect.NavigateBack }
        }
    }

    private fun loadMeal(mealId: String) {
        dispatch(MealDetailsContract.Action.UpdateLoading(true))
        launch(
            action = { mealsInteractor.getMealById(mealId) },
            onSuccess = {
                dispatch(
                    MealDetailsContract.Action.UpdateMeal(
                        meal = it?.toUi()?.first() ?: MealDetailsUi()
                    )
                )
                dispatch(MealDetailsContract.Action.UpdateLoading(false))
            },
            onError = {
                dispatch(MealDetailsContract.Action.UpdateLoading(false))
            }
        )
    }
}
