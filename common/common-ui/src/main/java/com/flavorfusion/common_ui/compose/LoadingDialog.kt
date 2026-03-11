package com.flavorfusion.common_ui.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@Composable
fun BlockingLoading(isDisplayed: Boolean) {
    BackHandler(enabled = isDisplayed) {}
    val keyboardController = LocalSoftwareKeyboardController.current

    if (isDisplayed) {
        keyboardController?.hide()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { }
                ),
            contentAlignment = Alignment.Center
        ) {
            AppProgressIndicator()
        }
    }
}

@Composable
fun FullScreenProgressIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = FlavorFusionTheme.colors.backgroundPrimary),
        contentAlignment = Alignment.Center
    ) {
        AppProgressIndicator()
    }
}

@Composable
fun AppProgressIndicator(size: Dp = 54.dp) {
    CircularProgressIndicator(
        modifier = Modifier.size(size),
        strokeCap = StrokeCap.Round,
        strokeWidth = 6.dp,
        color = FlavorFusionTheme.colors.colorPrimary
    )
}