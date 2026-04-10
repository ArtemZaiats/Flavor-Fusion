package com.flavorfusion.meals

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flavorfusion.common_ui.R
import com.flavorfusion.common_ui.compose.EffectHandler
import com.flavorfusion.common_ui.compose.design_system.placeholder.DefaultPlaceholder
import com.flavorfusion.common_ui.compose.design_system.pull_to_refresh.BoxWithPullToRefresh
import com.flavorfusion.common_ui.compose.design_system.toolbar.ToolbarWithSearchPanel
import com.flavorfusion.common_ui.model.meal.MealUi
import com.flavorfusion.common_ui.theme.FlavorFusionTheme
import com.flavorfusion.meals.compose.MealsGrid
import com.flavorfusion.meals.model.MealsDataPreviewProvider

@Composable
fun MealsScreen(
    navigateToMealDetails: (String) -> Unit
) {
    val viewModel: MealsViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    EffectHandler(viewModel = viewModel) {
        when (it) {
            is MealsContract.Effect.NavigateToMealDetails -> navigateToMealDetails(it.mealId)
        }
    }

    BackHandler(state.showSearch) {
        viewModel.setEvent(MealsContract.Event.OnSearchCloseClicked)
    }

    MealsScreen(
        state = state,
        onEvent = viewModel::handleEvent
    )
}

@Composable
fun MealsScreen(
    state: MealsContract.State,
    onEvent: (MealsContract.Event) -> Unit
) {
    DefaultPlaceholder(
        modifier = Modifier.fillMaxSize(),
        loading = state.loading,
        errorMessage = state.errorMessage,
        onRetry = { onEvent(MealsContract.Event.OnRetryClicked) }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            contentWindowInsets = WindowInsets(bottom = 0),
            topBar = {
                ToolbarWithSearchPanel(
                    title = stringResource(R.string.feature_meals_title),
                    searchPlaceholder = stringResource(R.string.feature_meals_search),
                    searchPanelVisible = state.showSearch,
                    searchValue = state.searchValue,
                    onSearchIconClicked = { onEvent.invoke(MealsContract.Event.OnSearchClicked) },
                    onSearchValueChanged = {
                        onEvent.invoke(MealsContract.Event.OnSearchValueChanged(it))
                    }
                )
            }
        ) { innerPadding ->
            BoxWithPullToRefresh(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                isRefreshing = state.refreshLoading,
                onRefresh = { onEvent(MealsContract.Event.OnRefresh) }
            ) {
                MealsGrid(
                    meals = state.searchMeals,
                    onMealClick = { onEvent(MealsContract.Event.OnMealClicked(it)) }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun MealsScreenPreview(
    @PreviewParameter(MealsDataPreviewProvider::class) meals: List<MealUi>
) {
    val state = MealsContract.State(
        meals = meals
    )
    FlavorFusionTheme {
        MealsScreen(state = state, onEvent = {})
    }
}
