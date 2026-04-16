package com.flavorfusion.drinks.drinkDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flavorfusion.common_ui.compose.EffectHandler
import com.flavorfusion.common_ui.compose.ItemDetailsContent
import com.flavorfusion.common_ui.model.drink.DrinkDetailsUi
import com.flavorfusion.common_ui.model.toItemDetailsUi
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun DrinkDetailsScreen(
    drinkId: String,
    onBackClick: () -> Unit
) {
    val viewModel: DrinkDetailsViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(drinkId) {
        if (drinkId.isNotEmpty()) {
            viewModel.setEvent(DrinkDetailsContract.Event.OnDrinkIdReceived(drinkId))
        }
    }

    EffectHandler(viewModel = viewModel) {
        when (it) {
            is DrinkDetailsContract.Effect.NavigateBack -> onBackClick()
        }
    }

    DrinkDetailsScreen(state = state, onEvent = viewModel::handleEvent)
}

@Composable
fun DrinkDetailsScreen(
    state: DrinkDetailsContract.State,
    onEvent: (DrinkDetailsContract.Event) -> Unit
) {
    ItemDetailsContent(
        isLoading = state.loading,
        item = state.drink.toItemDetailsUi(),
        onBackClick = { onEvent(DrinkDetailsContract.Event.OnIconBackClicked) }
    )
}

@PreviewLightDark
@Composable
fun DrinkDetailsPreview() {
    FlavorFusionTheme {
        ItemDetailsContent(
            isLoading = false,
            item = DrinkDetailsUi(
                drinkName = "A1",
                drinkImage = "",
                category = "Cocktail",
                instructions = "Shift the cocoa and sugar together into a medium-sized saucepan. Dissolve the cornstarch in the water, and stir into the cocoa and sugar until it is a smooth paste. Begin heating the mixture, stirring it with a whisk, and gradually pour in the milk. Continue stirring with the whisk as you bring the liquid to a simmer. Allow the chocolate to simmer for about 10 minutes, stirring often, until it is thick, glossy and completely smooth. Serve steaming hot in coffee mug. Serves six."
            ).toItemDetailsUi(),
            onBackClick = {}
        )
    }
}
