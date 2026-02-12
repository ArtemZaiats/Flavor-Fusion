package com.flavorfusion.drinks

import com.flavorfusion.common_domain.interactors.DrinksInteractor
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.common_ui.model.drink.toUi
import com.flavorfusion.core_ui.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    private val drinksInteractor: DrinksInteractor,
    private val executor: Executor,
    config: DrinksContract.Config
) : MviViewModel<DrinksContract.State, DrinksContract.Event>(config), Executor by executor {

    override fun handleEvent(event: DrinksContract.Event) {
        when (event) {
            is DrinksContract.Event.OnDrinkClicked -> onDrinkClicked(event.drink.drinkId)
            is DrinksContract.Event.OnSearchValueChanged -> {}
            DrinksContract.Event.OnSearchClicked -> {}
            DrinksContract.Event.OnSearchCloseClicked -> {}
            DrinksContract.Event.OnRefresh -> getDrinks()
        }
    }

    private fun getDrinks() {
        launch(
            action = { drinksInteractor.getDrinksByAlcoholic("Alcoholic") },
            onSuccess = {
                dispatch(
                    DrinksContract.Action.UpdateDrinks(
                        drinks = it?.toUi() ?: emptyList()
                    )
                )
            }
        )

    }

    private fun onDrinkClicked(drinkId: String) {
        publish { DrinksContract.Effect.NavigateToDrinkDetails(drinkId) }
    }
}