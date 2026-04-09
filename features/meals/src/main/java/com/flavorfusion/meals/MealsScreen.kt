package com.flavorfusion.meals

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
import com.flavorfusion.common_ui.compose.design_system.placeholder.DefaultPlaceholder
import com.flavorfusion.common_ui.compose.design_system.pull_to_refresh.BoxWithPullToRefresh
import com.flavorfusion.common_ui.compose.design_system.toolbar.ToolbarWithSingleAction
import com.flavorfusion.common_ui.model.meal.MealUi
import com.flavorfusion.common_ui.theme.FlavorFusionTheme
import com.flavorfusion.meals.compose.MealsGrid
import com.flavorfusion.meals.model.MealsDataPreviewProvider

@Composable
fun MealsScreen() {
    val viewModel: MealsViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

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
                ToolbarWithSingleAction(
                    navigationIcon = null,
                    actionVisible = false,
                    title = stringResource(R.string.feature_meals_title)
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
                    meals = state.meals,
                    onMealClick = {}
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
