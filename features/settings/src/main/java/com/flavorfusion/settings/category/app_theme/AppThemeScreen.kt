package com.flavorfusion.settings.category.app_theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flavorfusion.common_domain.model.app_theme.AppTheme
import com.flavorfusion.common_domain.model.app_theme.ThemeType
import com.flavorfusion.common_ui.compose.EffectHandler
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.ArrowLeft
import com.flavorfusion.common_ui.compose.design_system.icons.Check
import com.flavorfusion.common_ui.compose.design_system.toolbar.ToolbarWithSingleAction
import com.flavorfusion.common_ui.model.AppThemeUi
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun AppThemeScreen(
    navigateBack: () -> Unit
) {
    val viewModel: AppThemeViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    EffectHandler(viewModel = viewModel) {
        when (it) {
            is AppThemeContract.Effect.NavigateBack -> navigateBack.invoke()
        }
    }

    AppThemeScreen(
        state = state,
        onEvent = viewModel::handleEvent
    )
}

@Composable
fun AppThemeScreen(
    state: AppThemeContract.State,
    onEvent: (AppThemeContract.Event) -> Unit
) {
    Scaffold(
        topBar = {
            ToolbarWithSingleAction(
                navigationIcon = AppIcons.ArrowLeft,
                title = "App Theme",
                onNavigationIconClick = { onEvent.invoke(AppThemeContract.Event.OnBackClicked) }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            items(items = state.themes, key = { item -> item.theme.title }) {
                ThemeItem(
                    modifier = Modifier.fillMaxWidth(),
                    item = it,
                    onClick = { theme ->
                        onEvent.invoke(
                            AppThemeContract.Event.OnThemeSelected(theme)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun ThemeItem(modifier: Modifier = Modifier, item: AppThemeUi, onClick: (AppThemeUi) -> Unit) {
    Row(
        modifier = modifier
            .height(56.dp)
            .clickable {
                onClick.invoke(item)
            }
        .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.theme.title,
            color = FlavorFusionTheme.colors.contentPrimary
        )
        Spacer(modifier = modifier.weight(1f))
        if (item.isSelected) {
            Icon(
                imageVector = AppIcons.Check,
                contentDescription = "check mark",
                tint = FlavorFusionTheme.colors.colorPrimary,
            )
        }
    }
}

@Composable
@PreviewLightDark
@Preview(showBackground = true)
fun AppThemeScreenPreview() {
    val items = listOf(
        AppThemeUi(
            theme = AppTheme("Light", ThemeType.LIGHT),
            isSelected = false
        ),
        AppThemeUi(
            theme = AppTheme("Dark", ThemeType.DARK),
            isSelected = true
        ),
        AppThemeUi(
            theme = AppTheme("System", ThemeType.SYSTEM),
            isSelected = false
        ),
    )
    val state = AppThemeContract.State(themes = items)
    FlavorFusionTheme {
        AppThemeScreen(state = state, onEvent = {})
    }
}