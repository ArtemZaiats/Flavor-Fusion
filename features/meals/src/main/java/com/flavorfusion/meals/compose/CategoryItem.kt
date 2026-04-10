package com.flavorfusion.meals.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.flavorfusion.common_ui.compose.coilImageRequest
import com.flavorfusion.common_ui.model.meal.MealCategoryUi
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun CategoryItem(
    category: MealCategoryUi,
    onItemClick: (MealCategoryUi) -> Unit
) {
    val context = LocalContext.current
    val imageRequest = coilImageRequest(context, category.imageUrl)

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onItemClick(category) },
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = FlavorFusionTheme.colors.cardBackground
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(60.dp)
                    .width(90.dp)
                    .aspectRatio(16 / 9f)
                    .clip(RoundedCornerShape(4.dp)),
            )
            Text(
                text = category.name,
                style = FlavorFusionTheme.typography.bodyLMedium.copy(
                    color = FlavorFusionTheme.colors.contentPrimary
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CategoryItemPreview() {
    FlavorFusionTheme {
        CategoryItem(
            category = MealCategoryUi(
                name = "Beef",
                imageUrl = "https://www.themealdb.com/images/category/beef.png"
            ),
            onItemClick = {}
        )
    }
}