package com.flavorfusion.common_ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.flavorfusion.common_ui.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override */
)

val satoshiFamily = FontFamily(
    Font(R.font.satoshi_regular, FontWeight.Normal),
    Font(R.font.satoshi_medium, FontWeight.Medium),
    Font(R.font.satoshi_bold, FontWeight.Bold),
)

val flavorFusionTypography = FlavorFusionTypography(
    bodySMedium = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
    bodyMMedium = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodyLMedium = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 23.sp
    ),
    bodySRegular = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
    bodyMRegular = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodyLRegular = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 23.sp
    ),
    headingSMedium = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = TextUnit(0.2f, TextUnitType.Sp)
    ),
    headingMMedium = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        letterSpacing = TextUnit(0.2f, TextUnitType.Sp)
    ),
    headingLMedium = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        letterSpacing = TextUnit(0.2f, TextUnitType.Sp)
    ),
)