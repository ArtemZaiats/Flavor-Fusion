package com.flavorfusion.meals.mealDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flavorfusion.common_ui.compose.EffectHandler
import com.flavorfusion.common_ui.compose.ItemDetailsContent
import com.flavorfusion.common_ui.model.meal.MealDetailsUi
import com.flavorfusion.common_ui.model.toItemDetailsUi
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun MealDetailsScreen(
    mealId: String,
    onBackClick: () -> Unit
) {
    val viewModel: MealDetailsViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(mealId) {
        if (mealId.isNotEmpty()) {
            viewModel.setEvent(MealDetailsContract.Event.OnMealIdReceived(mealId))
        }
    }

    EffectHandler(viewModel = viewModel) {
        when (it) {
            is MealDetailsContract.Effect.NavigateBack -> onBackClick()
        }
    }

    MealDetailsScreen(state = state, onEvent = viewModel::handleEvent)
}

@Composable
fun MealDetailsScreen(
    state: MealDetailsContract.State,
    onEvent: (MealDetailsContract.Event) -> Unit
) {
    ItemDetailsContent(
        isLoading = state.loading,
        item = state.meal.toItemDetailsUi(),
        onBackClick = { onEvent(MealDetailsContract.Event.OnIconBackClicked) }
    )
}

@PreviewLightDark
@Composable
fun MealDetailsPreview() {
    FlavorFusionTheme {
        ItemDetailsContent(
            isLoading = false,
            item = MealDetailsUi(
                mealName = "Spaghetti Bolognese",
                mealImage = "",
                category = "Pasta",
                area = "Italian",
                instructions = "Bring a large pot of water to boil. Add spaghetti and cook until al dente. Meanwhile, brown the meat in a pan. Add tomato sauce and simmer for 20 minutes. Serve over spaghetti."
            ).toItemDetailsUi(),
            onBackClick = {}
        )
    }
}
