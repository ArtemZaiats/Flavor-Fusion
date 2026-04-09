package com.flavorfusion.meals.mealDetails

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.flavorfusion.common_ui.compose.CocktailLoading
import com.flavorfusion.common_ui.compose.EffectHandler
import com.flavorfusion.common_ui.compose.coilImageRequest
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.Close
import com.flavorfusion.common_ui.model.meal.MealDetailsUi
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
    if (state.loading) {
        CocktailLoading()
    } else {
        Column(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            MealHeader(
                meal = state.meal,
                onBackClick = { onEvent(MealDetailsContract.Event.OnIconBackClicked) }
            )
            MealDetails(meal = state.meal)
            Spacer(modifier = Modifier.height(96.dp))
        }
    }
}

@Composable
fun MealHeader(
    meal: MealDetailsUi,
    onBackClick: () -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            val context = LocalContext.current
            val imageRequest = coilImageRequest(context, meal.mealImage)

            AsyncImage(
                model = imageRequest,
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
                    )
            ) {
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
                .padding(16.dp)
        ) {
            Text(
                text = meal.mealName,
                style = FlavorFusionTheme.typography.headingLMedium.copy(
                    color = FlavorFusionTheme.colors.contentPrimary
                ),
                maxLines = 3,
                modifier = Modifier.fillMaxWidth(0.6f)
            )
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = meal.category,
                    style = FlavorFusionTheme.typography.bodyLMedium.copy(
                        color = FlavorFusionTheme.colors.contentPrimary
                    ),
                    modifier = Modifier
                        .background(color = Color(0xFF81E6F3), shape = RoundedCornerShape(50.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
                if (!meal.area.isNullOrBlank()) {
                    Text(
                        text = meal.area ?: "",
                        style = FlavorFusionTheme.typography.bodyLMedium.copy(
                            color = FlavorFusionTheme.colors.contentPrimary
                        ),
                        modifier = Modifier
                            .background(color = Color(0xFFFFC107).copy(alpha = 0.3f), shape = RoundedCornerShape(50.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MealDetails(
    meal: MealDetailsUi
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        meal.ingredients?.forEach {
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
            text = meal.instructions.orEmpty(),
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
fun MealDetailsPreview() {
    FlavorFusionTheme {
        MealDetailsScreen(
            state = MealDetailsContract.State(
                meal = MealDetailsUi(
                    mealName = "Spaghetti Bolognese",
                    mealImage = "",
                    category = "Pasta",
                    area = "Italian",
                    instructions = "Bring a large pot of water to boil. Add spaghetti and cook until al dente. Meanwhile, brown the meat in a pan. Add tomato sauce and simmer for 20 minutes. Serve over spaghetti."
                )
            ),
            onEvent = {}
        )
    }
}
