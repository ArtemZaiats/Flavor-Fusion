package com.flavorfusion.drinks.drinkDetails

import com.flavorfusion.common_domain.interactors.DrinksInteractor
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.common_ui.model.drink.DrinkDetailsUi
import com.flavorfusion.common_ui.model.drink.toUi
import com.flavorfusion.core_ui.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrinkDetailsViewModel @Inject constructor(
    private val drinksInteractor: DrinksInteractor,
    private val executor: Executor,
    config: DrinkDetailsContract.Config,
) : MviViewModel<DrinkDetailsContract.State, DrinkDetailsContract.Event>(config),
    Executor by executor {

    override fun handleEvent(event: DrinkDetailsContract.Event) {
        when (event) {
            is DrinkDetailsContract.Event.OnDrinkIdReceived -> loadDrink(event.drinkId)
            is DrinkDetailsContract.Event.OnIconBackClicked ->
                publish { DrinkDetailsContract.Effect.NavigateBack }
        }
    }

    private fun loadDrink(drinkId: String) {
        launch(
            action = { drinksInteractor.getDrinkById(drinkId) },
            onSuccess = {
                dispatch(
                    DrinkDetailsContract.Action.UpdateDrink(
                        drink = it?.toUi()?.first() ?: DrinkDetailsUi()
                    )
                )
            }
        )
    }


}