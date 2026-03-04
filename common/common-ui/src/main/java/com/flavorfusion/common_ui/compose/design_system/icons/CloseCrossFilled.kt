package com.flavorfusion.common_ui.compose.design_system.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

public val AppIcons.CloseCrossFilled: ImageVector
    get() {
        if (_closeCrossFilled != null) {
            return _closeCrossFilled!!
        }
        _closeCrossFilled =
            Builder(
                    name = "CloseCrossFilled",
                    defaultWidth = 24.0.dp,
                    defaultHeight = 24.0.dp,
                    viewportWidth = 24.0f,
                    viewportHeight = 24.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0xFFA77D3E)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd,
                    ) {
                        moveTo(12.0f, 0.0f)
                        curveToRelative(6.627f, 0.0f, 12.0f, 5.373f, 12.0f, 12.0f)
                        reflectiveCurveToRelative(-5.373f, 12.0f, -12.0f, 12.0f)
                        reflectiveCurveTo(0.0f, 18.627f, 0.0f, 12.0f)
                        reflectiveCurveTo(5.373f, 0.0f, 12.0f, 0.0f)
                        moveToRelative(4.707f, 7.293f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, false, -1.414f, 0.0f)
                        lineTo(12.0f, 10.586f)
                        lineTo(8.707f, 7.293f)
                        arcToRelative(1.0f, 1.0f, 0.0f, true, false, -1.414f, 1.414f)
                        lineTo(10.586f, 12.0f)
                        lineToRelative(-3.293f, 3.293f)
                        arcToRelative(1.0f, 1.0f, 0.0f, true, false, 1.414f, 1.414f)
                        lineTo(12.0f, 13.414f)
                        lineToRelative(3.293f, 3.293f)
                        arcToRelative(1.0f, 1.0f, 0.0f, true, false, 1.414f, -1.414f)
                        lineTo(13.414f, 12.0f)
                        lineToRelative(3.293f, -3.293f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, false, 0.0f, -1.414f)
                    }
                }
                .build()
        return _closeCrossFilled!!
    }

private var _closeCrossFilled: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.CloseCrossFilled, contentDescription = null)
    }
}
