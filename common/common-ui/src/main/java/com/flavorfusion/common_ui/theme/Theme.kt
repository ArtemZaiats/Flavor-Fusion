package com.flavorfusion.common_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.flavorfusion.common_domain.model.app_theme.ThemeType

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = ColorBackgroundPrimaryDark
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = ColorBackgroundPrimaryLight
)

object FlavorFusionTheme {
    val colors: FlavorFusionColors
        @Composable
        get() = LocalFlavorFusionColors.current
}


@Composable
fun FlavorFusionTheme(
    appTheme: ThemeType = ThemeType.SYSTEM,
    // Dynamic color is available on Android 12+, disable for now
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val darkTheme = when (appTheme) {
        ThemeType.SYSTEM -> isSystemInDarkTheme()
        ThemeType.LIGHT -> false
        ThemeType.DARK -> true
    }


    val colorScheme = when(appTheme) {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        ThemeType.SYSTEM -> if (darkTheme) DarkColorScheme else LightColorScheme
        ThemeType.LIGHT -> LightColorScheme
        ThemeType.DARK -> DarkColorScheme
    }

    val flavorFusionColors = when(appTheme) {
        ThemeType.SYSTEM -> if (darkTheme) darkFlavorFusionColorScheme() else lightFlavorFusionColorScheme()
        ThemeType.LIGHT -> lightFlavorFusionColorScheme()
        ThemeType.DARK -> darkFlavorFusionColorScheme()
    }


    CompositionLocalProvider(
        LocalFlavorFusionColors provides flavorFusionColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}