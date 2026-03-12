package com.flavorfusion.common_ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.flavorfusion.common_ui.error.ErrorMessageExtractor
import com.flavorfusion.common_ui.error.FakeErrorMessageExtractor

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

@Immutable
data class FlavorFusionTypography(
    val bodySMedium: TextStyle,
    val bodyMMedium: TextStyle,
    val bodyLMedium: TextStyle,
    val bodySRegular: TextStyle,
    val bodyMRegular: TextStyle,
    val bodyLRegular: TextStyle,
    val headingSMedium: TextStyle,
    val headingMMedium: TextStyle,
    val headingLMedium: TextStyle
)

val LocalFlavorFusionColors = staticCompositionLocalOf<FlavorFusionColors> { error("No colors provided") }

val LocalFlavorFusionTypography = staticCompositionLocalOf<FlavorFusionTypography> { error("No font provided") }

val LocalErrorMessageExtractor = compositionLocalOf<ErrorMessageExtractor> { FakeErrorMessageExtractor() }