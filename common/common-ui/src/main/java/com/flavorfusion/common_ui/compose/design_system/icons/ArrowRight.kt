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

public val AppIcons.ArrowRight: ImageVector
    get() {
        if (_arrowright != null) {
            return _arrowright!!
        }
        _arrowright =
            Builder(
                    name = "ArrowRight",
                    defaultWidth = 20.0.dp,
                    defaultHeight = 20.0.dp,
                    viewportWidth = 20.0f,
                    viewportHeight = 20.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0xFF000000)),
                        stroke = SolidColor(Color(0x00000000)),
                        strokeLineWidth = 1.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd,
                    ) {
                        moveTo(4.866f, 19.708f)
                        lineTo(4.866f, 19.708f)
                        curveTo(5.271f, 20.098f, 5.926f, 20.098f, 6.33f, 19.708f)
                        lineTo(14.893f, 11.444f)
                        curveTo(15.702f, 10.664f, 15.702f, 9.397f, 14.893f, 8.617f)
                        lineTo(6.268f, 0.292f)
                        curveTo(5.867f, -0.093f, 5.22f, -0.098f, 4.814f, 0.282f)
                        lineTo(4.814f, 0.282f)
                        curveTo(4.4f, 0.671f, 4.395f, 1.312f, 4.803f, 1.707f)
                        lineTo(12.697f, 9.324f)
                        curveTo(13.102f, 9.714f, 13.102f, 10.347f, 12.697f, 10.738f)
                        lineTo(4.866f, 18.294f)
                        curveTo(4.461f, 18.685f, 4.461f, 19.318f, 4.866f, 19.708f)
                    }
                }
                .build()
        return _arrowright!!
    }

private var _arrowright: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.ArrowRight, contentDescription = null)
    }
}
