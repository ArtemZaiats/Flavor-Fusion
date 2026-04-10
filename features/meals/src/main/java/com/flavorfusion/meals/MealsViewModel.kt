package com.flavorfusion.meals

import androidx.lifecycle.viewModelScope
import com.flavorfusion.common_domain.interactors.MealsInteractor
import com.flavorfusion.common_domain.model.onSuccess
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.common_ui.model.meal.MealCategoryUi
import com.flavorfusion.common_ui.model.meal.toUi
import com.flavorfusion.core_ui.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
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
        getCategories()
    }

    override fun handleEvent(event: MealsContract.Event) {
        when (event) {
            MealsContract.Event.OnRefresh -> getMeals(
                isInitial = false,
                isRefreshing = true,
                category = state.value.selectedCategory
            )

            MealsContract.Event.OnRetryClicked -> getCategories()
            MealsContract.Event.OnSearchClicked -> handleSearchPanelVisibility()
            MealsContract.Event.OnSearchCloseClicked -> handleOnSearchClose()
            MealsContract.Event.OnCategoryClicked -> publish { MealsContract.Effect.ShowCategoriesDialog }
            is MealsContract.Event.OnMealClicked -> publish {
                MealsContract.Effect.NavigateToMealDetails(event.meal.mealId)
            }

            is MealsContract.Event.OnSearchValueChanged -> handleSearchValueChanged(event.value)
            is MealsContract.Event.OnCategorySelected -> selectCategory(event.category)
        }
    }

    private fun getCategories() {
        launch(
            action = {
                dispatch(MealsContract.Action.Loading(show = true, errorMessage = null))
                mealsInteractor.getCategories()
            },
            onSuccess = { categories ->
                val selectedCategory = categories
                    ?.toUi()
                    ?.firstOrNull()
                    ?.copy(isSelected = true)

                dispatch(
                    MealsContract.Action.UpdateCategories(
                        categories = categories?.toUi() ?: emptyList()
                    )
                )
                selectedCategory?.let {
                    selectCategory(it)
                }
            },
            onError = { errorMessage ->
                dispatch(
                    MealsContract.Action.Loading(
                        show = false,
                        errorMessage = errorMessage
                    )
                )
            }
        )
    }

    private fun selectCategory(category: MealCategoryUi) {
        dispatch(MealsContract.Action.UpdateSelectedCategory(category = category))
        getMeals(category)
    }

    private fun getMeals(
        category: MealCategoryUi,
        isInitial: Boolean = true,
        isRefreshing: Boolean = false
    ) {
        launch(
            action = {
                dispatch(
                    MealsContract.Action.Loading(
                        show = isInitial,
                        errorMessage = currentState.errorMessage
                    )
                )
                dispatch(MealsContract.Action.RefreshLoading(show = isRefreshing))
                mealsInteractor.getMealsByCategory(category.name)
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
            }
            .launchIn(viewModelScope)
    }

    private fun handleOnSearchClose() {
        dispatch(MealsContract.Action.UpdateShowSearch(showSearch = false))
        handleSearchValueChanged("")
    }
}
