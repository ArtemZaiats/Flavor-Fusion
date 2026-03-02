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

public val AppIcons.ArrowLeft: ImageVector
    get() {
        if (_arrowLeft != null) {
            return _arrowLeft!!
        }
        _arrowLeft =
            Builder(
                    name = "ArrowLeft",
                    defaultWidth = 20.0.dp,
                    defaultHeight = 20.0.dp,
                    viewportWidth = 24.0f,
                    viewportHeight = 24.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0xFF0F0F0F)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(16.18f, 3.269f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, false, -1.415f, 0.0f)
                        lineTo(8.121f, 9.913f)
                        arcToRelative(3.0f, 3.0f, 0.0f, false, false, -0.001f, 4.242f)
                        lineToRelative(6.57f, 6.575f)
                        arcToRelative(1.0f, 1.0f, 0.0f, true, false, 1.415f, -1.414f)
                        lineToRelative(-6.573f, -6.572f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, true, 0.0f, -1.414f)
                        lineToRelative(6.648f, -6.647f)
                        arcToRelative(1.0f, 1.0f, 0.0f, false, false, 0.0f, -1.414f)
                    }
                }
                .build()
        return _arrowLeft!!
    }

private var _arrowLeft: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.ArrowLeft, contentDescription = null)
    }
}
