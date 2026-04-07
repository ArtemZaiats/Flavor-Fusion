package com.flavorfusion.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flavorfusion.common_ui.compose.EffectHandler
import com.flavorfusion.common_ui.compose.design_system.button.PrimaryButton
import com.flavorfusion.common_ui.theme.FlavorFusionTheme
import com.flavorfusion.settings.compose.ProfileHeader
import com.flavorfusion.settings.compose.SettingsCategoryComponent
import com.flavorfusion.settings.model.SettingsDataPreviewProvider

@Composable
fun SettingsScreen(
    navigateToAppTheme: () -> Unit
) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    SettingsScreen(
        state = state,
        onEvent = viewModel::handleEvent
    )

    EffectHandler(viewModel = viewModel) {
        when (it) {
            is SettingsContract.Effect.NavigateToAppTheme -> navigateToAppTheme.invoke()
        }
    }

    SettingsScreen(state = state, onEvent = viewModel::handleEvent)
}

@Composable
fun SettingsScreen(
    state: SettingsContract.State,
    onEvent: (SettingsContract.Event) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            modifier = Modifier
                .padding(paddingValues)
                .padding(start = 16.dp, end = 16.dp, top = 30.dp)
        ) {
            ProfileHeader(profile = state.profile)
            Column(verticalArrangement = Arrangement.spacedBy(30.dp)) {
                state.categories.forEach { category ->
                    SettingsCategoryComponent(
                        category = category.title,
                        items = category.items,
                        onItemClick = { onEvent.invoke(SettingsContract.Event.OnItemClicked(it)) }
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            PrimaryButton(
                text = "Log out",
                onClick = { onEvent.invoke(SettingsContract.Event.OnLogOutClicked) },
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun SettingsScreenPreview() {
    val categories = SettingsDataPreviewProvider().values.toList()
    val state = SettingsContract.State(categories = categories)
    FlavorFusionTheme {
        SettingsScreen(state = state, onEvent = {})
    }
}