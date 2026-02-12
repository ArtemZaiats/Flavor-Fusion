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
            is DrinksContract.Event.OnSearchValueChanged -> handleSearchValueChanged(event.value)
            DrinksContract.Event.OnSearchClicked -> handleSearchPanelVisibility()
            DrinksContract.Event.OnSearchCloseClicked -> handleOnSearchClose()
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
                handleSearch(currentState.searchValue)
            }
        )
    }

    private fun onDrinkClicked(drinkId: String) {
        publish { DrinksContract.Effect.NavigateToDrinkDetails(drinkId) }
    }

    private fun handleSearchPanelVisibility() {
        dispatch(DrinksContract.Action.UpdateShowSearch(showSearch = !state.value.showSearch))
        handleSearchValueChanged("")
    }

    private fun handleSearchValueChanged(searchValue: String) {
        dispatch(DrinksContract.Action.UpdateSearchValue(searchValue = searchValue))
        handleSearch(searchValue)
    }

    private fun handleSearch(searchValue: String) {
        val allDrinks = currentState.drinks
        val searchDrinks = if (searchValue.isEmpty()) {
            allDrinks
        } else {
            allDrinks.filter { drink ->
                drink.drinkName.contains(searchValue, ignoreCase = true)
            }
        }
        dispatch(DrinksContract.Action.UpdateSearchDrinks(searchDrinks = searchDrinks))
    }

    private fun handleOnSearchClose() {
        dispatch(DrinksContract.Action.UpdateShowSearch(showSearch = false))
        handleSearchValueChanged("")
    }
}