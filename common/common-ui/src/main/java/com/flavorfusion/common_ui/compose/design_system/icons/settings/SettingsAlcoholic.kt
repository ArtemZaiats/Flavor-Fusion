package com.flavorfusion.common_ui.compose.design_system.icons.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons

public val AppIcons.SettingsAlcoholic: ImageVector
    get() {
        if (_settingsalcoholic != null) {
            return _settingsalcoholic!!
        }
        _settingsalcoholic =
            Builder(
                    name = "SettingsAlcoholic",
                    defaultWidth = 800.0.dp,
                    defaultHeight = 800.0.dp,
                    viewportWidth = 420.0f,
                    viewportHeight = 420.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0xFF000000)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(173.38f, 135.27f)
                        verticalLineTo(31.84f)
                        curveToRelative(0.0f, -3.78f, -3.07f, -6.85f, -6.85f, -6.85f)
                        horizontalLineToRelative(-3.16f)
                        lineToRelative(3.18f, -16.87f)
                        curveToRelative(0.38f, -2.0f, -0.16f, -4.07f, -1.46f, -5.64f)
                        curveTo(163.8f, 0.91f, 161.87f, 0.0f, 159.83f, 0.0f)
                        horizontalLineToRelative(-31.12f)
                        curveToRelative(-2.04f, 0.0f, -3.97f, 0.91f, -5.27f, 2.48f)
                        curveToRelative(-1.3f, 1.57f, -1.84f, 3.64f, -1.46f, 5.64f)
                        lineToRelative(3.18f, 16.87f)
                        horizontalLineToRelative(-3.16f)
                        curveToRelative(-3.78f, 0.0f, -6.85f, 3.07f, -6.85f, 6.85f)
                        verticalLineToRelative(103.43f)
                        curveToRelative(-10.26f, 6.42f, -39.03f, 26.64f, -39.03f, 51.56f)
                        verticalLineTo(413.15f)
                        curveToRelative(0.0f, 3.78f, 3.07f, 6.85f, 6.85f, 6.85f)
                        horizontalLineToRelative(122.59f)
                        curveToRelative(3.78f, 0.0f, 6.85f, -3.07f, 6.85f, -6.85f)
                        verticalLineTo(186.83f)
                        curveTo(212.41f, 161.91f, 183.65f, 141.69f, 173.38f, 135.27f)
                        close()
                        moveTo(137.68f, 339.19f)
                        horizontalLineToRelative(-43.98f)
                        verticalLineToRelative(-94.3f)
                        horizontalLineToRelative(43.98f)
                        verticalLineTo(339.19f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFF000000)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(332.47f, 306.05f)
                        curveToRelative(19.79f, -31.77f, 8.99f, -85.1f, 3.42f, -106.55f)
                        curveToRelative(-0.96f, -3.7f, -4.3f, -6.28f, -8.12f, -6.28f)
                        horizontalLineToRelative(-89.25f)
                        curveToRelative(-3.82f, 0.0f, -7.16f, 2.58f, -8.12f, 6.28f)
                        curveToRelative(-5.56f, 21.45f, -16.37f, 74.78f, 3.42f, 106.55f)
                        curveToRelative(7.98f, 12.81f, 20.17f, 20.63f, 36.23f, 23.26f)
                        verticalLineToRelative(64.51f)
                        horizontalLineToRelative(-18.64f)
                        curveToRelative(-7.22f, 0.0f, -13.09f, 5.87f, -13.09f, 13.09f)
                        curveToRelative(0.0f, 7.22f, 5.87f, 13.09f, 13.09f, 13.09f)
                        horizontalLineToRelative(63.46f)
                        curveToRelative(7.22f, 0.0f, 13.09f, -5.87f, 13.09f, -13.09f)
                        reflectiveCurveToRelative(-5.87f, -13.09f, -13.09f, -13.09f)
                        horizontalLineToRelative(-18.64f)
                        verticalLineToRelative(-64.51f)
                        curveTo(312.3f, 326.68f, 324.49f, 318.86f, 332.47f, 306.05f)
                        close()
                    }
                }
                .build()
        return _settingsalcoholic!!
    }

private var _settingsalcoholic: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.SettingsAlcoholic, contentDescription = null)
    }
}
