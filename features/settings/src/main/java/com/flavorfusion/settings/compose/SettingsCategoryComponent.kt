package com.flavorfusion.settings.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flavorfusion.common_ui.R
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.ArrowRight
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsAppTheme
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsGlass
import com.flavorfusion.common_ui.compose.design_system.icons.settings.SettingsLanguage
import com.flavorfusion.common_ui.compose.design_system.switch.BaseSwitch
import com.flavorfusion.common_ui.theme.FlavorFusionTheme
import com.flavorfusion.settings.model.Category
import com.flavorfusion.settings.model.CategoryItem
import com.flavorfusion.settings.model.MenuItem

@Composable
fun SettingsCategoryComponent(
    modifier: Modifier = Modifier,
    category: String,
    items: List<MenuItem>,
    onItemClick: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                color = FlavorFusionTheme.colors.contentPrimary
            )
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items.forEach {
                SettingsMenuItemComponent(
                    modifier = Modifier.fillMaxWidth(),
                    item = it,
                    onItemClick = onItemClick
                )
            }
        }
    }
}

@Composable
fun SettingsMenuItemComponent(
    modifier: Modifier = Modifier,
    item: MenuItem,
    onItemClick: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(48.dp)
            .background(
                color = FlavorFusionTheme.colors.backgroundSecondary,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .alpha(if (item.isActive) 1f else 0.5f)
            .then(if (item.isActive) Modifier.clickable { onItemClick(item.id) } else Modifier)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Icon(
                imageVector = item.icon,
                contentDescription = stringResource(R.string.feature_settings_menu_item_icon),
                modifier = Modifier.size(20.dp),
                tint = FlavorFusionTheme.colors.contentPrimary
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyMedium,
                color = FlavorFusionTheme.colors.contentPrimary,
                lineHeight = 20.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        if (item.showSwitch) {
            BaseSwitch(
                modifier = Modifier.scale(0.85f),
                checked = item.isSwitchActive,
                onCheckedChange = { onItemClick(item.id) }
            )
        } else {
            Icon(
                imageVector = AppIcons.ArrowRight,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = FlavorFusionTheme.colors.contentSecondary

            )
        }
    }
}

@PreviewLightDark
@Composable
private fun SettingsCategoryComponentPreview() {
    val items = listOf(
        MenuItem(
            id = CategoryItem.LANGUAGE.id,
            isActive = false,
            title = "Language",
            icon = AppIcons.SettingsLanguage,
            category = Category.APP,
        ),
        MenuItem(
            id = CategoryItem.APP_THEME.id,
            title = "App theme",
            icon = AppIcons.SettingsAppTheme,
            category = Category.APP,
        ),
        MenuItem(
            id = CategoryItem.SHOW_ALCOHOLIC.id,
            title = "Show alcoholic drinks",
            icon = AppIcons.SettingsGlass,
            category = Category.CONTENT_PREFERENCES,
            showSwitch = true,
            isSwitchActive = true
        )
    )
    val category = Category.CONTENT_PREFERENCES.title
    FlavorFusionTheme {
        SettingsCategoryComponent(
            modifier = Modifier
                .background(color = FlavorFusionTheme.colors.backgroundPrimary)
                .padding(8.dp),
            category = category,
            items = items,
            onItemClick = {}
        )
    }
}