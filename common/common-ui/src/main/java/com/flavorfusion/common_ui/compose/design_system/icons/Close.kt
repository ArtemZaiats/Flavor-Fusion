package com.flavorfusion.common_ui.compose.design_system.icons

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

public val AppIcons.Close: ImageVector
    get() {
        if (_close != null) {
            return _close!!
        }
        _close =
            Builder(
                    name = "Close",
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
                        pathFillType = NonZero,
                    ) {
                        moveTo(21.293f, 1.293f)
                        arcToRelative(1.0f, 1.0f, 0.0f, true, true, 1.414f, 1.414f)
                        lineTo(13.414f, 12.0f)
                        lineToRelative(9.293f, 9.293f)
                        arcToRelative(1.0f, 1.0f, 0.0f, true, true, -1.414f, 1.414f)
                        lineTo(12.0f, 13.414f)
                        lineToRelative(-9.293f, 9.293f)
                        arcToRelative(1.0f, 1.0f, 0.0f, true, true, -1.414f, -1.414f)
                        lineTo(10.586f, 12.0f)
                        lineTo(1.293f, 2.707f)
                        arcToRelative(1.0f, 1.0f, 0.0f, true, true, 1.414f, -1.414f)
                        lineTo(12.0f, 10.586f)
                        close()
                    }
                }
                .build()
        return _close!!
    }

private var _close: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Close, contentDescription = null)
    }
}
