package com.flavorfusion.settings.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.flavorfusion.common_ui.compose.design_system.BaseDialog
import com.flavorfusion.common_ui.compose.design_system.button.PrimaryButton
import com.flavorfusion.common_ui.compose.design_system.button.SecondaryButton
import com.flavorfusion.common_ui.theme.FlavorFusionTheme
import com.flavorfusion.settings.model.DialogData

@Composable
fun LogOutDialog(
    properties: DialogProperties = DialogProperties(),
    dialogData: DialogData = DialogData(),
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    BaseDialog(
        properties = properties,
        onDismissRequest = onDismiss,
        modifier = Modifier.padding(20.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = dialogData.title,
                style = FlavorFusionTheme.typography.headingMMedium,
                color = FlavorFusionTheme.colors.contentPrimary
            )
            if (dialogData.message.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = dialogData.message,
                    style = FlavorFusionTheme.typography.bodyLMedium,
                    color = FlavorFusionTheme.colors.contentPrimary
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 24.dp)
            ) {
                PrimaryButton(
                    text = dialogData.confirmButtonText,
                    onClick = {
                        onConfirm.invoke()
                        onDismiss.invoke()
                    }
                )
                SecondaryButton(
                    text = dialogData.cancelButtonText,
                    onClick = onDismiss
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun LogOutDialogPreview() {
    FlavorFusionTheme {
        LogOutDialog(
            dialogData = DialogData(
                title = "Do you really want to Log out?",
                message = "Are you sure you want to log out?",
                confirmButtonText = "Log out",
                cancelButtonText = "Cancel"
            ),
            onConfirm = {},
            onDismiss = {}
        )
    }
}