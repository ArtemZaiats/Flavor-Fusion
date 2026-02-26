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

public val AppIcons.SettingsLanguage: ImageVector
    get() {
        if (_settingslanguage != null) {
            return _settingslanguage!!
        }
        _settingslanguage =
            Builder(
                    name = "SettingsLanguage",
                    defaultWidth = 800.0.dp,
                    defaultHeight = 800.0.dp,
                    viewportWidth = 48.0f,
                    viewportHeight = 48.0f,
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
                        moveTo(43.8f, 41.2f)
                        lineTo(33.9f, 16.3f)
                        arcTo(2.1f, 2.1f, 0.0f, false, false, 32.0f, 15.0f)
                        horizontalLineTo(30.0f)
                        arcToRelative(2.1f, 2.1f, 0.0f, false, false, -1.9f, 1.3f)
                        lineTo(23.3f, 28.4f)
                        arcToRelative(24.0f, 24.0f, 0.0f, false, true, -5.6f, -4.3f)
                        curveToRelative(3.4f, -4.0f, 5.9f, -8.8f, 6.2f, -13.1f)
                        horizontalLineToRelative(2.0f)
                        arcTo(2.1f, 2.1f, 0.0f, false, false, 28.0f, 9.3f)
                        arcTo(2.0f, 2.0f, 0.0f, false, false, 26.0f, 7.0f)
                        horizontalLineTo(17.5f)
                        verticalLineTo(4.1f)
                        arcTo(2.1f, 2.1f, 0.0f, false, false, 15.8f, 2.0f)
                        arcToRelative(2.0f, 2.0f, 0.0f, false, false, -2.3f, 2.0f)
                        verticalLineTo(7.0f)
                        horizontalLineTo(6.1f)
                        arcTo(2.1f, 2.1f, 0.0f, false, false, 4.0f, 8.7f)
                        arcTo(2.0f, 2.0f, 0.0f, false, false, 6.0f, 11.0f)
                        horizontalLineTo(19.9f)
                        curveToRelative(-0.3f, 3.0f, -2.3f, 6.7f, -4.9f, 10.1f)
                        arcToRelative(34.1f, 34.1f, 0.0f, false, true, -3.2f, -4.9f)
                        arcTo(2.1f, 2.1f, 0.0f, false, false, 9.6f, 15.0f)
                        arcToRelative(2.0f, 2.0f, 0.0f, false, false, -1.4f, 2.9f)
                        arcToRelative(39.1f, 39.1f, 0.0f, false, false, 4.1f, 6.2f)
                        arcToRelative(24.0f, 24.0f, 0.0f, false, true, -7.0f, 5.0f)
                        arcTo(2.2f, 2.2f, 0.0f, false, false, 4.0f, 31.4f)
                        arcTo(2.0f, 2.0f, 0.0f, false, false, 6.0f, 33.0f)
                        lineToRelative(0.8f, -0.2f)
                        arcTo(26.4f, 26.4f, 0.0f, false, false, 15.0f, 27.0f)
                        arcToRelative(28.1f, 28.1f, 0.0f, false, false, 6.8f, 5.1f)
                        lineToRelative(-3.6f, 9.1f)
                        arcTo(2.0f, 2.0f, 0.0f, false, false, 20.0f, 44.0f)
                        arcToRelative(2.2f, 2.2f, 0.0f, false, false, 1.9f, -1.3f)
                        lineTo(25.8f, 33.0f)
                        horizontalLineTo(36.2f)
                        lineToRelative(3.9f, 9.7f)
                        arcTo(2.2f, 2.2f, 0.0f, false, false, 42.0f, 44.0f)
                        arcToRelative(2.0f, 2.0f, 0.0f, false, false, 1.8f, -2.8f)
                        close()
                        moveTo(27.4f, 29.0f)
                        lineTo(31.0f, 19.9f)
                        lineTo(34.6f, 29.0f)
                        close()
                    }
                }
                .build()
        return _settingslanguage!!
    }

private var _settingslanguage: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.SettingsLanguage, contentDescription = null)
    }
}
