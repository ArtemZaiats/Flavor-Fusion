package com.flavorfusion.common_ui.compose.design_system.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons
import com.flavorfusion.common_ui.compose.design_system.icons.Check
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun TertiaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingIcon: ImageVector? = null,
    textStyles: TextStyle = FlavorFusionTheme.typography.bodyMMedium,
    onClick: () -> Unit
) {
    val primary = FlavorFusionTheme.colors.colorPrimary
    TextButton(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(contentColor = primary),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp, max = 52.dp)
    ) {
        if (loading) {
            CircularProgressIndicator(
                color = primary,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                leadingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    style = textStyles
                )
            }
        }
    }
}

private class TertiaryButtonEnabledProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(false, true)
}

@PreviewLightDark
@Composable
private fun TertiaryButtonPreview(
    @PreviewParameter(TertiaryButtonEnabledProvider::class) enabled: Boolean
) {
    FlavorFusionTheme {
        TertiaryButton(
            text = "Continue",
            enabled = enabled,
            leadingIcon = AppIcons.Check,
            onClick = {}
        )
    }
}
