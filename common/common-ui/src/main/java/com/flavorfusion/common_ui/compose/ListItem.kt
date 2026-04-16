package com.flavorfusion.common_ui.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun <T> ListItem(
    modifier: Modifier = Modifier,
    name: String,
    image: String,
    isFavorite: Boolean,
    item: T,
    onItemClick: (T) -> Unit,
    onFavoriteClick: (T) -> Unit
) {
    val context = LocalContext.current
    val imageRequest = coilImageRequest(context, image)

    val favoriteColor by animateColorAsState(
        targetValue = if (isFavorite) FlavorFusionTheme.colors.colorPrimary else Color.White,
        animationSpec = tween(durationMillis = 300),
        label = "favoriteColor"
    )

    var isImageLoading by remember { mutableStateOf(true) }
    val imageAlpha by animateFloatAsState(
        targetValue = if (isImageLoading) 0f else 1f,
        animationSpec = tween(durationMillis = 400),
        label = "imageAlpha"
    )

    val scrimGradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color(0xCC000000))
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = "$name card" }
            .clip(RoundedCornerShape(16.dp))
            .clickable { onItemClick(item) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = FlavorFusionTheme.colors.cardBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 10f)
        ) {
            if (isImageLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(FlavorFusionTheme.colors.cardBackground)
                )
            }

            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                onLoading = { isImageLoading = true },
                onSuccess = { isImageLoading = false },
                onError = { isImageLoading = false },
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(imageAlpha)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(88.dp)
                    .align(Alignment.BottomCenter)
                    .background(scrimGradient)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                Text(
                    text = name,
                    style = FlavorFusionTheme.typography.bodyMMedium.copy(color = Color.White),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { onFavoriteClick(item) },
                    modifier = Modifier.minimumInteractiveComponentSize()
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = favoriteColor
                    )
                }
            }
        }
    }
}
