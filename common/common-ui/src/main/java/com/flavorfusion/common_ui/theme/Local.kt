package com.flavorfusion.common_ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class FlavorFusionColors(
    val background: Color,
    val backgroundSecondary: Color,
    val cardBackground: Color,
    val textPrimary: Color,
    val isDark: Boolean
)

fun lightFlavorFusionColorScheme() = FlavorFusionColors(
    background = ColorBackgroundPrimaryLight,
    backgroundSecondary = ColorBackgroundSecondaryLight,
    cardBackground = ColorCardBackgroundLight,
    textPrimary = ColorTextPrimaryLight,
    isDark = false
)

fun darkFlavorFusionColorScheme() = FlavorFusionColors(
    background = ColorBackgroundPrimaryDark,
    cardBackground = ColorCardBackgroundDark,
    backgroundSecondary = ColorBackgroundSecondaryDark,
    textPrimary = ColorTextPrimaryDark,
    isDark = true
)

val LocalFlavorFusionColors = staticCompositionLocalOf<FlavorFusionColors> { error("No colors provided") }