package com.flavorfusion.settings.category.edit_user

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.flavorfusion.common_ui.R
import com.flavorfusion.common_ui.compose.EffectHandler
import com.flavorfusion.common_ui.compose.design_system.button.PrimaryButton
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.ArrowLeft
import com.flavorfusion.common_ui.compose.design_system.icons.PersonPlaceholder
import com.flavorfusion.common_ui.compose.design_system.toolbar.ToolbarWithSingleAction
import com.flavorfusion.common_ui.theme.FlavorFusionColors
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun EditUserScreen(
    navigateBack: () -> Unit
) {
    val viewModel: EditUserViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    EffectHandler(viewModel = viewModel) { effect ->
        when (effect) {
            is EditUserContract.Effect.NavigateBack -> navigateBack()
        }
    }

    EditUserScreen(state = state, onEvent = viewModel::handleEvent)
}

@Composable
fun EditUserScreen(
    state: EditUserContract.State,
    onEvent: (EditUserContract.Event) -> Unit
) {
    val colors = FlavorFusionTheme.colors

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        onEvent(EditUserContract.Event.OnImagePicked(uri?.toString()))
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colors.backgroundPrimary,
        topBar = {
            ToolbarWithSingleAction(
                navigationIcon = AppIcons.ArrowLeft,
                title = stringResource(R.string.feature_settings_edit_user_screen_title),
                actionVisible = false,
                onNavigationIconClick = { onEvent(EditUserContract.Event.OnBackClicked) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .navigationBarsPadding()
                .imePadding()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            AvatarPicker(
                pickedUri = state.pickedImageUri,
                avatarUrl = state.initialAvatarUrl,
                onClick = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.feature_settings_edit_user_change_photo),
                style = FlavorFusionTheme.typography.bodyMRegular,
                color = colors.colorPrimary,
                modifier = Modifier.clickable {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = state.firstName,
                onValueChange = { onEvent(EditUserContract.Event.OnFirstNameChanged(it)) },
                label = { Text(stringResource(R.string.feature_settings_edit_user_first_name)) },
                singleLine = true,
                colors = outlinedTextFieldColors(colors),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = state.lastName,
                onValueChange = { onEvent(EditUserContract.Event.OnLastNameChanged(it)) },
                label = { Text(stringResource(R.string.feature_settings_edit_user_last_name)) },
                singleLine = true,
                colors = outlinedTextFieldColors(colors),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = stringResource(R.string.feature_settings_edit_user_save),
                enabled = state.hasChanges && !state.isSaving,
                loading = state.isSaving,
                onClick = { onEvent(EditUserContract.Event.OnSaveClicked) }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun AvatarPicker(
    pickedUri: String?,
    avatarUrl: String,
    onClick: () -> Unit
) {
    val colors = FlavorFusionTheme.colors
    val previewModel = pickedUri ?: avatarUrl.takeIf { it.isNotEmpty() }

    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (previewModel != null) {
            AsyncImage(
                model = previewModel,
                contentDescription = stringResource(R.string.feature_settings_profile_header_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Image(
                imageVector = AppIcons.PersonPlaceholder,
                contentDescription = stringResource(R.string.feature_settings_profile_header_image_description),
                modifier = Modifier.size(96.dp),
                colorFilter = ColorFilter.tint(colors.contentPrimary)
            )
        }
    }
}

@Composable
private fun outlinedTextFieldColors(colors: FlavorFusionColors) =
    OutlinedTextFieldDefaults.colors(
        focusedBorderColor = colors.inputFieldBorderActive,
        unfocusedBorderColor = colors.contentSecondary.copy(alpha = 0.4f),
        focusedLabelColor = colors.colorPrimary,
        unfocusedLabelColor = colors.contentSecondary,
        cursorColor = colors.colorPrimary,
        focusedTextColor = colors.contentPrimary,
        unfocusedTextColor = colors.contentPrimary,
        errorBorderColor = colors.error,
        errorLabelColor = colors.error,
        errorSupportingTextColor = colors.error
    )

@PreviewLightDark
@Composable
private fun EditUserScreenPreview() {
    FlavorFusionTheme {
        EditUserScreen(
            state = EditUserContract.State(
                email = "john.doe@example.com",
                initialFirstName = "John",
                initialLastName = "Doe",
                firstName = "John",
                lastName = "Doe"
            ),
            onEvent = {}
        )
    }
}
