package com.flavorfusion.drinks

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flavorfusion.common_ui.R
import com.flavorfusion.common_ui.compose.EffectHandler
import com.flavorfusion.common_ui.compose.design_system.ToolbarWithSearchPanel
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.Search
import com.flavorfusion.common_ui.model.drink.DrinkUi
import com.flavorfusion.common_ui.theme.FlavorFusionTheme
import com.flavorfusion.core_ui.compose.OnLifecycleEvent
import com.flavorfusion.drinks.compose.DrinksGrid
import com.flavorfusion.drinks.model.DrinksDataPreviewProvider

@Composable
fun DrinksScreen(
    navigateToDrinkDetails: (String) -> Unit
) {
    val viewModel: DrinksViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    OnLifecycleEvent(
        onCreate = { viewModel.setEvent(DrinksContract.Event.OnRefresh) }
    )

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
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        ToolbarWithSearchPanel(
            title = stringResource(R.string.feature_drinks_title),
            searchIcon = AppIcons.Search,
            searchPlaceholder = stringResource(R.string.feature_drinks_search),
            searchPanelVisible = state.showSearch,
            searchValue = state.searchValue,
            onSearchIconClicked = { onEvent.invoke(DrinksContract.Event.OnSearchClicked) },
            onSearchValueChanged = { onEvent.invoke(DrinksContract.Event.OnSearchValueChanged(it)) }
        )
        DrinksGrid(
            drinks = state.searchDrinks,
            onDrinkClick = { onEvent.invoke(DrinksContract.Event.OnDrinkClicked(it)) }
        )
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