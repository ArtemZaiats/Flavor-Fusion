package com.flavorfusion.common_ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
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

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
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