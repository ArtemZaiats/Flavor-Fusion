package com.flavorfusion.meals

import androidx.lifecycle.viewModelScope
import coil.util.CoilUtils.result
import com.flavorfusion.common_domain.interactors.MealsInteractor
import com.flavorfusion.common_domain.model.onSuccess
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.common_ui.model.meal.toUi
import com.flavorfusion.core_ui.mvi.MviViewModel
import com.flavorfusion.meals.MealsContract.Effect.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val mealsInteractor: MealsInteractor,
    private val executor: Executor,
    config: MealsContract.Config
) : MviViewModel<MealsContract.State, MealsContract.Event>(config), Executor by executor {

    private val searchQuery = MutableStateFlow("")

    init {
        setupSearch()
        getMeals()
    }

    override fun handleEvent(event: MealsContract.Event) {
        when (event) {
            MealsContract.Event.OnRefresh -> getMeals(isInitial = false, isRefreshing = true)
            MealsContract.Event.OnRetryClicked -> getMeals()
            is MealsContract.Event.OnMealClicked -> publish {
                NavigateToMealDetails(event.meal.mealId)
            }
            MealsContract.Event.OnSearchClicked -> handleSearchPanelVisibility()
            MealsContract.Event.OnSearchCloseClicked -> handleOnSearchClose()
            is MealsContract.Event.OnSearchValueChanged -> handleSearchValueChanged(event.value)
        }
    }

    private fun getMeals(isInitial: Boolean = true, isRefreshing: Boolean = false) {
        launch(
            action = {
                dispatch(
                    MealsContract.Action.Loading(
                        show = isInitial,
                        errorMessage = currentState.errorMessage
                    )
                )
                dispatch(MealsContract.Action.RefreshLoading(show = isRefreshing))
                // TODO: Refactor categories logic
                mealsInteractor.getMealsByCategory("Seafood")
            },
            onSuccess = {
                dispatch(
                    MealsContract.Action.UpdateMeals(
                        meals = it?.toUi()?.sortedBy { it.mealName } ?: emptyList()
                    )
                )
                dispatch(
                    MealsContract.Action.UpdateSearchMeals(
                        searchMeals = it?.toUi() ?: emptyList()
                    )
                )
                dispatch(MealsContract.Action.HideAllLoadings)
            },
            onError = { errorMessage ->
                if (currentState.meals.isEmpty()) {
                    dispatch(
                        MealsContract.Action.Loading(
                            show = false,
                            errorMessage = errorMessage
                        )
                    )
                } else {
                    dispatch(MealsContract.Action.HideAllLoadings)
                }
            }
        )
    }

    private fun handleSearchPanelVisibility() {
        dispatch(MealsContract.Action.UpdateShowSearch(showSearch = !state.value.showSearch))
        handleSearchValueChanged("")
    }

    private fun handleSearchValueChanged(searchValue: String) {
        dispatch(MealsContract.Action.UpdateSearchValue(searchValue = searchValue))
        if (searchValue.isEmpty()) {
            dispatch(
                MealsContract.Action.UpdateSearchMeals(
                    searchMeals = state.value.meals
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
                    mealsInteractor.getMealByNameFlow(query)
                } else {
                    emptyFlow()
                }
            }
            .onEach { result ->
                result.onSuccess { meals ->
                    dispatch(
                        MealsContract.Action.UpdateSearchMeals(
                            searchMeals = meals?.toUi() ?: emptyList()
                        )
                    )
                }
            }.launchIn(viewModelScope)
    }

    private fun handleOnSearchClose() {
        dispatch(MealsContract.Action.UpdateShowSearch(showSearch = false))
        handleSearchValueChanged("")
    }
}
