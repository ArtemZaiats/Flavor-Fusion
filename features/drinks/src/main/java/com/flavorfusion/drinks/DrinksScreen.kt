package com.flavorfusion.drinks

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
import com.flavorfusion.common_ui.compose.design_system.toolbar.ToolbarWithSearchPanel
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.Search
import com.flavorfusion.common_ui.compose.design_system.placeholder.DefaultPlaceholder
import com.flavorfusion.common_ui.compose.design_system.pull_to_refresh.BoxWithPullToRefresh
import com.flavorfusion.common_ui.model.drink.DrinkUi
import com.flavorfusion.common_ui.theme.FlavorFusionTheme
import com.flavorfusion.drinks.compose.DrinksGrid
import com.flavorfusion.drinks.model.DrinksDataPreviewProvider

@Composable
fun DrinksScreen(
    navigateToDrinkDetails: (String) -> Unit
) {
    val viewModel: DrinksViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    EffectHandler(viewModel = viewModel) {
        when (it) {
            is DrinksContract.Effect.NavigateToDrinkDetails -> navigateToDrinkDetails(it.drinkId)
        }
    }

    BackHandler(state.showSearch) {
        viewModel.setEvent(DrinksContract.Event.OnSearchCloseClicked)
    }

    DrinksScreen(
        state = state,
        onEvent = viewModel::handleEvent
    )
}

@Composable
fun DrinksScreen(
    state: DrinksContract.State,
    onEvent: (DrinksContract.Event) -> Unit
) {
    DefaultPlaceholder(
        modifier = Modifier
            .fillMaxSize(),
        loading = state.loading,
        errorMessage = state.errorMessage,
        onRetry = { onEvent.invoke(DrinksContract.Event.OnRefresh) }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            contentWindowInsets = WindowInsets(bottom = 0),
            topBar = {
                ToolbarWithSearchPanel(
                    title = stringResource(R.string.feature_drinks_title),
                    searchPlaceholder = stringResource(R.string.feature_drinks_search),
                    searchPanelVisible = state.showSearch,
                    searchValue = state.searchValue,
                    onSearchIconClicked = { onEvent.invoke(DrinksContract.Event.OnSearchClicked) },
                    onSearchValueChanged = {
                        onEvent.invoke(
                            DrinksContract.Event.OnSearchValueChanged(
                                it
                            )
                        )
                    }
                )
            }
        ) { innerPadding ->
            BoxWithPullToRefresh(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                isRefreshing = state.refreshLoading,
                onRefresh = { onEvent.invoke(DrinksContract.Event.OnRefresh) }
            ) {
                DrinksGrid(
                    drinks = state.searchDrinks,
                    onDrinkClick = { onEvent.invoke(DrinksContract.Event.OnDrinkClicked(it)) }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun DrinksScreenPreview(
    @PreviewParameter(DrinksDataPreviewProvider::class) drinks: List<DrinkUi>
) {
    val state = DrinksContract.State(
        searchDrinks = drinks,
    )
    FlavorFusionTheme {
        DrinksScreen(state = state, onEvent = {})
    }
}