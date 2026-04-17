package com.flavorfusion.common_ui.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.ArrowLeft
import com.flavorfusion.common_ui.compose.design_system.video.YoutubePlayer
import com.flavorfusion.common_ui.model.ItemDetailsUi
import com.flavorfusion.common_ui.theme.ColorTagPrimary
import com.flavorfusion.common_ui.theme.ColorTagSecondary
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun ItemDetailsContent(
    isLoading: Boolean,
    item: ItemDetailsUi,
    onBackClick: () -> Unit
) {
    if (isLoading) {
        CocktailLoading()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            ItemHeader(item = item, onBackClick = onBackClick)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (!item.ingredients.isNullOrEmpty()) {
                    IngredientsCard(item.ingredients)
                }
                if (!item.instructions.isNullOrEmpty()) {
                    InstructionsCard(item.instructions)
                }
            }
            item.videoUrl?.let {
                Spacer(modifier = Modifier.height(24.dp))
                YoutubePlayer(videoId = it.extractYoutubeVideoId())
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ItemHeader(item: ItemDetailsUi, onBackClick: () -> Unit) {
    val context = LocalContext.current

    val heroScrim = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black)
    )

    var isImageLoading by remember { mutableStateOf(true) }
    val imageAlpha by animateFloatAsState(
        targetValue = if (isImageLoading) 0f else 1f,
        animationSpec = tween(durationMillis = 400),
        label = "imageAlpha"
    )

    Box(modifier = Modifier.fillMaxWidth()) {
        if (isImageLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4 / 3.3f)
                    .background(FlavorFusionTheme.colors.backgroundSecondary)
            )
        }

        AsyncImage(
            model = coilImageRequest(context, item.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            onLoading = { isImageLoading = true },
            onSuccess = { isImageLoading = false },
            onError = { isImageLoading = false },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4 / 3.3f)
                .alpha(imageAlpha)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .align(Alignment.BottomCenter)
                .background(heroScrim)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .padding(start = 8.dp)
                .minimumInteractiveComponentSize()
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.85f), CircleShape)
                .clickable { onBackClick() }
        ) {
            Icon(
                imageVector = AppIcons.ArrowLeft,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.padding(4.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = item.name,
                style = FlavorFusionTheme.typography.headingLMedium.copy(color = Color.White),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Tag(label = item.category, background = ColorTagPrimary)
                if (!item.area.isNullOrBlank()) {
                    Tag(label = item.area, background = ColorTagSecondary)
                }
            }
        }
    }
}

@Composable
private fun Tag(label: String, background: Color) {
    Text(
        text = label,
        style = FlavorFusionTheme.typography.bodySMedium.copy(color = Color.White),
        modifier = Modifier
            .background(background, RoundedCornerShape(50.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Composable
private fun IngredientsCard(ingredients: Map<String?, String?>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = FlavorFusionTheme.colors.cardBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Ingredients",
                style = FlavorFusionTheme.typography.headingMMedium.copy(
                    color = FlavorFusionTheme.colors.contentPrimary
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            val entries = ingredients.entries.toList()
            entries.forEachIndexed { index, entry ->
                IngredientRow(name = entry.key.orEmpty(), measure = entry.value ?: "")
                if (index < entries.lastIndex) {
                    HorizontalDivider(
                        color = FlavorFusionTheme.colors.contentSecondary.copy(alpha = 0.15f)
                    )
                }
            }
        }
    }
}

@Composable
private fun IngredientRow(name: String, measure: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = name,
            style = FlavorFusionTheme.typography.bodyMMedium.copy(
                color = FlavorFusionTheme.colors.contentPrimary
            ),
            modifier = Modifier.fillMaxWidth(0.5f)
        )
        Text(
            text = measure,
            style = FlavorFusionTheme.typography.bodyMRegular.copy(
                color = FlavorFusionTheme.colors.contentSecondary
            ),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
private fun InstructionsCard(instructions: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = FlavorFusionTheme.colors.cardBackground)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Instructions",
                style = FlavorFusionTheme.typography.headingMMedium.copy(
                    color = FlavorFusionTheme.colors.contentPrimary
                )
            )
            Text(
                text = instructions,
                style = FlavorFusionTheme.typography.bodyLRegular.copy(
                    color = FlavorFusionTheme.colors.contentSecondary
                )
            )
        }
    }
}
