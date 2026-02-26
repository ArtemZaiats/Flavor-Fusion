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
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons

public val AppIcons.SettingsGlass: ImageVector
    get() {
        if (_settingsGlass != null) {
            return _settingsGlass!!
        }
        _settingsGlass =
            Builder(
                    name = "SettingsGlass",
                    defaultWidth = 20.0.dp,
                    defaultHeight = 20.0.dp,
                    viewportWidth = 44.819f,
                    viewportHeight = 44.819f,
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
                        moveTo(23.415f, 21.764f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, -0.689f, -1.726f)
                        lineToRelative(14.074f, -13.353f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.377f, 1.451f)
                        lineToRelative(-14.074f, 13.353f)
                        arcTo(1.0f, 1.0f, 0.0f, false, true, 23.415f, 21.764f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 2.0f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(31.922f, 3.416f)
                    }
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 2.0f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(33.967f, 1.476f)
                    }
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 1.0f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(31.67f, 3.387f)
                    }
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 1.0f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(33.945f, 1.389f)
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
                        moveTo(21.394f, 21.764f)
                        arcToRelative(0.994f, 0.994f, 0.0f, false, true, -0.688f, -0.275f)
                        lineToRelative(-14.073f, -13.353f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.377f, -1.451f)
                        lineToRelative(14.073f, 13.353f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, -0.689f, 1.726f)
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
                        moveTo(23.717f, 41.491f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, -0.975f, -0.781f)
                        curveToRelative(-0.1f, -0.446f, -2.424f, -11.022f, -0.05f, -20.3f)
                        arcToRelative(1.637f, 1.637f, 0.0f, false, true, 0.054f, -0.223f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.921f, 0.554f)
                        arcToRelative(0.987f, 0.987f, 0.0f, false, true, -0.021f, 0.1f)
                        curveToRelative(-2.283f, 8.834f, 0.024f, 19.326f, 0.047f, 19.431f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, -0.756f, 1.195f)
                        arcTo(1.035f, 1.035f, 0.0f, false, true, 23.717f, 41.491f)
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
                        moveTo(20.948f, 41.495f)
                        arcToRelative(1.042f, 1.042f, 0.0f, false, true, -0.22f, -0.024f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, -0.757f, -1.195f)
                        curveToRelative(0.024f, -0.106f, 2.357f, -10.75f, 0.009f, -19.614f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.934f, -0.512f)
                        curveToRelative(2.475f, 9.346f, 0.11f, 20.111f, 0.008f, 20.564f)
                        arcTo(1.0f, 1.0f, 0.0f, false, true, 20.948f, 41.495f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 2.0f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(30.107f, 1.501f)
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
                        moveTo(22.405f, 44.819f)
                        curveToRelative(-5.042f, 0.0f, -10.469f, -1.258f, -10.469f, -4.02f)
                        curveToRelative(0.0f, -2.669f, 4.852f, -3.554f, 6.939f, -3.811f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, 0.244f, 1.985f)
                        curveToRelative(-3.781f, 0.465f, -5.14f, 1.517f, -5.183f, 1.828f)
                        curveToRelative(0.082f, 0.6f, 3.063f, 2.018f, 8.469f, 2.018f)
                        reflectiveCurveToRelative(8.387f, -1.417f, 8.468f, -2.022f)
                        curveToRelative(-0.043f, -0.311f, -1.418f, -1.371f, -5.245f, -1.831f)
                        arcToRelative(1.0f, 1.0f, 0.0f, true, true, 0.239f, -1.987f)
                        curveToRelative(2.106f, 0.254f, 7.006f, 1.133f, 7.006f, 3.82f)
                        curveTo(32.873f, 43.561f, 27.447f, 44.819f, 22.405f, 44.819f)
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
                        moveTo(22.402f, 22.061f)
                        arcToRelative(3.022f, 3.022f, 0.0f, false, true, -2.273f, -1.053f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.508f, -1.315f)
                        arcToRelative(1.012f, 1.012f, 0.0f, false, false, 1.622f, -0.118f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.649f, 1.133f)
                        arcTo(3.059f, 3.059f, 0.0f, false, true, 22.402f, 22.061f)
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
                        moveTo(22.405f, 12.011f)
                        curveToRelative(-8.3f, 0.0f, -16.7f, -2.063f, -16.7f, -6.005f)
                        reflectiveCurveToRelative(8.4f, -6.006f, 16.7f, -6.006f)
                        reflectiveCurveToRelative(16.695f, 2.062f, 16.695f, 6.006f)
                        reflectiveCurveTo(30.701f, 12.011f, 22.405f, 12.011f)
                        close()
                        moveTo(22.405f, 2.0f)
                        curveToRelative(-8.97f, 0.0f, -14.7f, 2.372f, -14.7f, 4.006f)
                        reflectiveCurveToRelative(5.725f, 4.005f, 14.7f, 4.005f)
                        reflectiveCurveToRelative(14.695f, -2.372f, 14.695f, -4.005f)
                        reflectiveCurveTo(31.375f, 2.001f, 22.405f, 2.001f)
                        close()
                    }
                }
                .build()
        return _settingsGlass!!
    }

private var _settingsGlass: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.SettingsGlass, contentDescription = null)
    }
}
