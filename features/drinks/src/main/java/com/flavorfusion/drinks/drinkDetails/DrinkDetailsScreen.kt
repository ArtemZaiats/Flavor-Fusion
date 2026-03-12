package com.flavorfusion.drinks.drinkDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.flavorfusion.common_ui.compose.EffectHandler
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.Close
import com.flavorfusion.common_ui.model.drink.DrinkDetailsUi
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun DrinkDetailsScreen(
    drinkId: String, onBackClick: () -> Unit
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
    state: DrinkDetailsContract.State, onEvent: (DrinkDetailsContract.Event) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        DrinkHeader(
            drinkImage = state.drink.drinkImage ?: "",
            drinkName = state.drink.drinkName,
            drink = state.drink,
            onBackClick = { onEvent(DrinkDetailsContract.Event.OnIconBackClicked) })
        DrinkDetails(drink = state.drink)
        Spacer(modifier = Modifier.height(96.dp))
    }
}

@Composable
fun DrinkHeader(
    drinkImage: String, drinkName: String, drink: DrinkDetailsUi, onBackClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = drinkImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4 / 3.3f)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .size(32.dp)
                    .clip(shape = CircleShape)
                    .clickable { onBackClick() }
                    .background(color = Color.White, shape = CircleShape)
                    .border(
                        width = 1.dp, color = Color.LightGray, shape = CircleShape
                    )) {
                Icon(
                    imageVector = AppIcons.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(4.dp),
                    tint = FlavorFusionTheme.colors.contentPrimary
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = drinkName,
                style = FlavorFusionTheme.typography.headingLMedium.copy(
                    color = FlavorFusionTheme.colors.contentPrimary
                ),
                maxLines = 3,
                modifier = Modifier.fillMaxWidth(0.6f)
            )
            Text(
                text = drink.category,
                style = FlavorFusionTheme.typography.bodyLMedium.copy(
                    color = FlavorFusionTheme.colors.contentPrimary
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = Color(0xFF81E6F3), shape = RoundedCornerShape(50.dp))
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun DrinkDetails(
    drink: DrinkDetailsUi,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        drink.ingredients?.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = it.key.orEmpty(),
                    style = FlavorFusionTheme.typography.bodyLMedium.copy(
                        color = FlavorFusionTheme.colors.contentPrimary
                    ),
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
                Text(
                    text = it.value ?: "",
                    style = FlavorFusionTheme.typography.bodyLMedium.copy(
                        color = FlavorFusionTheme.colors.contentPrimary
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Text(
            text = drink.instructions.orEmpty(),
            style = FlavorFusionTheme.typography.bodyLMedium.copy(
                color = FlavorFusionTheme.colors.contentPrimary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}

@PreviewLightDark
@Composable
fun DrinkDetailsPreview() {
    DrinkDetailsScreen(
        state = DrinkDetailsContract.State(
            drink = DrinkDetailsUi(
                drinkName = "A1",
                drinkImage = "",
                category = "Cocktail",
            )
        ),
        onEvent = {}
    )
}