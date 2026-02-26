package com.flavorfusion.common_ui.compose.design_system.switch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.flavorfusion.common_ui.theme.ColorContentSecondaryDark
import com.flavorfusion.common_ui.theme.ColorPrimary
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun BaseSwitch(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Switch(
        enabled = enabled,
        checked = checked,
        thumbContent = {},
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors().copy(
            checkedTrackColor = ColorPrimary,
            checkedThumbColor = Color.White,
            uncheckedTrackColor = ColorContentSecondaryDark,
            uncheckedBorderColor = ColorContentSecondaryDark,
            uncheckedThumbColor = Color.White,
        )
    )
}

@Preview
@Composable
fun BaseSwitchPreview() {
    FlavorFusionTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = FlavorFusionTheme.colors.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            BaseSwitch(checked = true, onCheckedChange = {})
            BaseSwitch(checked = false, onCheckedChange = {})
        }
    }
}