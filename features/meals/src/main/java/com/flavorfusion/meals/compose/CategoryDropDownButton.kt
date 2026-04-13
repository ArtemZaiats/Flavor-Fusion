package com.flavorfusion.meals.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.ArrowDownSmall
import com.flavorfusion.common_ui.theme.ColorSecondary
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun CategoryDropDownButton(
    title: String,
    onClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .clickable { onClick() }
            .background(color = ColorSecondary, shape = RoundedCornerShape(999.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = title,
            style = FlavorFusionTheme.typography.bodyMMedium.copy(
                color = FlavorFusionTheme.colors.contentPrimary
            )
        )

        Icon(
            imageVector = AppIcons.ArrowDownSmall,
            contentDescription = null,
            tint = FlavorFusionTheme.colors.contentPrimary
        )
    }
}

@PreviewLightDark
@Composable
fun CategoryDropDownButtonPreview() {
    FlavorFusionTheme {
        CategoryDropDownButton(
            title = "Chicken",
            onClick = {}
        )
    }
}