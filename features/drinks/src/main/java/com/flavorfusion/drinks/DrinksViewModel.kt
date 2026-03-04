package com.flavorfusion.drinks

import androidx.lifecycle.viewModelScope
import com.flavorfusion.common_domain.interactors.DrinksInteractor
import com.flavorfusion.common_domain.interactors.SettingsInteractor
import com.flavorfusion.common_domain.model.onSuccess
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.common_ui.model.drink.toUi
import com.flavorfusion.core_ui.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    private val drinksInteractor: DrinksInteractor,
    private val settingsInteractor: SettingsInteractor,
    private val executor: Executor,
    config: DrinksContract.Config
) : MviViewModel<DrinksContract.State, DrinksContract.Event>(config), Executor by executor {

    private val searchQuery = MutableStateFlow("")

    override fun handleEvent(event: DrinksContract.Event) {
        when (event) {
            is DrinksContract.Event.OnDrinkClicked -> onDrinkClicked(event.drink.drinkId)
            is DrinksContract.Event.OnSearchValueChanged -> handleSearchValueChanged(event.value)
            DrinksContract.Event.OnSearchClicked -> handleSearchPanelVisibility()
            DrinksContract.Event.OnSearchCloseClicked -> handleOnSearchClose()
            DrinksContract.Event.OnRefresh -> getDrinks()
        }
    }

    init {
        setupSearch()
        getDrinks()
    }

    private fun getDrinks() {
        launch(
            action = {
                drinksInteractor.getDrinksByAlcoholic(
                    showAlcoholic = settingsInteractor.getShowAlcoholicFlow().first()
                )
            },
            onSuccess = {
                dispatch(
                    DrinksContract.Action.UpdateDrinks(
                        drinks = it?.toUi()?.sortedBy { it.drinkName } ?: emptyList()
                    )
                )
                dispatch(
                    DrinksContract.Action.UpdateSearchDrinks(
                        searchDrinks = it?.toUi() ?: emptyList()
                    )
                )
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
        if (searchValue.isEmpty()) {
            dispatch(
                DrinksContract.Action.UpdateSearchDrinks(
                    searchDrinks = state.value.drinks
                )
            )
            return
        }
        searchQuery.value = searchValue
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun setupSearch() {
        searchQuery
            .debounce(300)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                if (query.isNotEmpty()) {
                    drinksInteractor.getDrinkByNameFlow(query)
                } else {
                    emptyFlow()
                }
            }
            .onEach { result ->
                result.onSuccess { drinks ->
                    dispatch(
                        DrinksContract.Action.UpdateSearchDrinks(
                            searchDrinks = drinks?.toUi() ?: emptyList()
                        )
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun handleOnSearchClose() {
        dispatch(DrinksContract.Action.UpdateShowSearch(showSearch = false))
        handleSearchValueChanged("")
    }
}