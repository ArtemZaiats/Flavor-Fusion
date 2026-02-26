package com.flavorfusion.common_ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class FlavorFusionColors(
    val colorPrimary: Color,
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val cardBackground: Color,
    val contentPrimary: Color,
    val contentSecondary: Color,
    val inputFieldBorderActive: Color,
    val isDark: Boolean
)

fun lightFlavorFusionColorScheme() = FlavorFusionColors(
    colorPrimary = ColorPrimary,
    backgroundPrimary = ColorBackgroundPrimaryLight,
    backgroundSecondary = ColorBackgroundSecondaryLight,
    cardBackground = ColorCardBackgroundLight,
    contentPrimary = ColorContentPrimaryLight,
    contentSecondary = ColorContentSecondaryLight,
    inputFieldBorderActive = ColorInputFieldBorderActiveLight,
    isDark = false
)

fun darkFlavorFusionColorScheme() = FlavorFusionColors(
    colorPrimary = ColorPrimary,
    backgroundPrimary = ColorBackgroundPrimaryDark,
    cardBackground = ColorCardBackgroundDark,
    backgroundSecondary = ColorBackgroundSecondaryDark,
    contentPrimary = ColorContentPrimaryDark,
    contentSecondary = ColorContentSecondaryDark,
    inputFieldBorderActive = ColorInputFieldBorderActiveDark,
    isDark = true
)

val LocalFlavorFusionColors = staticCompositionLocalOf<FlavorFusionColors> { error("No colors provided") }