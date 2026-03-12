package com.flavorfusion.common_ui.compose.design_system.toolbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.CloseCrossFilled
import com.flavorfusion.common_ui.compose.design_system.icons.Search
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun ToolbarWithSearchPanel(
    modifier: Modifier = Modifier,
    searchIcon: ImageVector,
    actionIcon: ImageVector? = null,
    title: String,
    searchPanelVisible: Boolean = false,
    actionVisible: Boolean = false,
    onSearchIconClicked: () -> Unit = {},
    onActionIconClicked: () -> Unit = {},
    searchValue: String,
    onSearchValueChanged: (String) -> Unit = {},
    searchPlaceholder: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 20.dp)
            .height(64.dp)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onSearchIconClicked
        ) {
            Icon(
                imageVector = searchIcon,
                contentDescription = "Navigation icon",
                tint = FlavorFusionTheme.colors.contentPrimary
            )
        }
        Box(modifier = Modifier.align(Alignment.Center)) {
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = searchPanelVisible,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(500)
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(500)
                )
            ) {
                SearchPanel(
                    searchValue = searchValue,
                    placeholder = searchPlaceholder,
                    onSearchValueChanged = onSearchValueChanged,
                    actionVisible = actionVisible
                )
            }
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = searchPanelVisible.not(),
                enter = slideInHorizontally(
                    initialOffsetX = { it * 2 },
                    animationSpec = tween(500)
                ) + fadeIn(animationSpec = tween(500)),
                exit = slideOutHorizontally(
                    targetOffsetX = { it * 2 },
                    animationSpec = tween(500)
                ) + fadeOut(animationSpec = tween(500))
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = FlavorFusionTheme.typography.bodyLMedium.copy(
                        color = FlavorFusionTheme.colors.contentPrimary
                    )
                )
            }
        }
        if (actionVisible) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = { onActionIconClicked() }
            ) {
                actionIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "Action icon",
                        tint = FlavorFusionTheme.colors.contentPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun SearchPanel(
    modifier: Modifier = Modifier,
    searchValue: String,
    placeholder: String,
    onSearchValueChanged: (String) -> Unit,
    actionVisible: Boolean
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TextField(
        modifier = modifier
            .padding(start = 48.dp, end = if (actionVisible) 48.dp else 0.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(16.dp),
                color = FlavorFusionTheme.colors.inputFieldBorderActive
            )
            .focusRequester(focusRequester),
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        value = searchValue,
        trailingIcon = {
            if (searchValue.isNotEmpty() || searchValue.isNotBlank())
                Image(
                    imageVector = AppIcons.CloseCrossFilled,
                    colorFilter = ColorFilter.tint(FlavorFusionTheme.colors.contentPrimary),
                    contentDescription = "",
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = { onSearchValueChanged("") })
                )
        },
        placeholder = { Text(placeholder) },
        onValueChange = { onSearchValueChanged(it) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}

@Composable
@PreviewLightDark
fun ToolbarWithSearchPanelPreview() {
    FlavorFusionTheme {
        ToolbarWithSearchPanel(
            searchIcon = AppIcons.Search,
            title = "Search",
            searchPanelVisible = true,
            searchValue = "",
            onSearchValueChanged = {},
            searchPlaceholder = "Search",
            actionVisible = false
        )
    }
}