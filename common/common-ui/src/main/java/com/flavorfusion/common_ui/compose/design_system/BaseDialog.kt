package com.flavorfusion.common_ui.compose.design_system

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun BaseDialog(
    modifier: Modifier = Modifier,
    properties: DialogProperties,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = FlavorFusionTheme.colors.backgroundSecondary
        ) {
            Column(
              modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )
        }
    }
}