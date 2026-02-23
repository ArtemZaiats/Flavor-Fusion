package com.flavorfusion.common_ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class FlavorFusionColors(
    val background: Color,
    val isDark: Boolean
)

fun lightFlavorFusionColorScheme() = FlavorFusionColors(
    background = ColorBackgroundMainLight,
    isDark = false
)

fun darkFlavorFusionColorScheme() = FlavorFusionColors(
    background = ColorBackgroundMainDark,
    isDark = true
)

val LocalFlavorFusionColors = staticCompositionLocalOf<FlavorFusionColors> { error("No colors provided") }