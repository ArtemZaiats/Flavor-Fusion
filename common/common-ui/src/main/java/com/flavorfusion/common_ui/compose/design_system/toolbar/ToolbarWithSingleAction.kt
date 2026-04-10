package com.flavorfusion.common_ui.compose.design_system.toolbar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.ArrowLeft
import com.flavorfusion.common_ui.compose.design_system.icons.ArrowRight
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarWithSingleAction(
    modifier: Modifier = Modifier,
    navigationIcon: ImageVector?,
    actionIcon: ImageVector? = null,
    title: String,
    actionVisible: Boolean = true,
    onNavigationIconClick: () -> Unit = {},
    onActionIconClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = FlavorFusionTheme.colors.backgroundPrimary,
        ),
        navigationIcon = {
            navigationIcon?.let {
                IconButton(onClick = { onNavigationIconClick.invoke() }) {
                    Icon(
                        imageVector = it,
                        contentDescription = "Navigation Icon",
                        tint = FlavorFusionTheme.colors.contentPrimary,
                        modifier = Modifier.size(40.dp).padding(8.dp)
                    )
                }
            }
        },
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                color = FlavorFusionTheme.colors.contentPrimary,
                style = FlavorFusionTheme.typography.headingMMedium
            )
        },
        actions = {
            if (actionVisible) {
                IconButton(onClick = { onActionIconClick.invoke() }) {
                    actionIcon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = "Action Icon",
                            tint = FlavorFusionTheme.colors.contentPrimary,
                            modifier = Modifier.size(40.dp).padding(10.dp)
                        )
                    }
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun ToolbarWithSingleActionPreview() {
    FlavorFusionTheme {
        ToolbarWithSingleAction(
            navigationIcon = AppIcons.ArrowLeft,
            actionIcon = AppIcons.ArrowRight,
            title = "Toolbar Title"
        )
    }
}