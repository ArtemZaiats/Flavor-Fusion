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

public val AppIcons.ArrowDownSmall: ImageVector
    get() {
        if (_arrowDownSmall != null) {
            return _arrowDownSmall!!
        }
        _arrowDownSmall =
            Builder(
                    name = "ArrowDownSmall",
                    defaultWidth = 24.0.dp,
                    defaultHeight = 24.0.dp,
                    viewportWidth = 24.0f,
                    viewportHeight = 24.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0xFF292D32)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(17.919f, 8.18f)
                        horizontalLineTo(11.689f)
                        horizontalLineTo(6.079f)
                        curveTo(5.119f, 8.18f, 4.639f, 9.34f, 5.319f, 10.02f)
                        lineTo(10.499f, 15.2f)
                        curveTo(11.329f, 16.03f, 12.679f, 16.03f, 13.509f, 15.2f)
                        lineTo(15.479f, 13.23f)
                        lineTo(18.689f, 10.02f)
                        curveTo(19.359f, 9.34f, 18.879f, 8.18f, 17.919f, 8.18f)
                        close()
                    }
                }
                .build()
        return _arrowDownSmall!!
    }

private var _arrowDownSmall: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.ArrowDownSmall, contentDescription = null)
    }
}
