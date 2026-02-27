package com.flavorfusion.common_ui.compose.design_system.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

public val AppIcons.PersonPlaceholder: ImageVector
    get() {
        if (_personplaceholder != null) {
            return _personplaceholder!!
        }
        _personplaceholder =
            Builder(
                    name = "PersonPlaceholder",
                    defaultWidth = 40.0.dp,
                    defaultHeight = 40.0.dp,
                    viewportWidth = 16.0f,
                    viewportHeight = 16.0f,
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
                        moveTo(11.0f, 6.0f)
                        arcToRelative(3.0f, 3.0f, 0.0f, true, true, -6.0f, 0.0f)
                        arcToRelative(3.0f, 3.0f, 0.0f, false, true, 6.0f, 0.0f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFF000000)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd,
                    ) {
                        moveTo(0.0f, 8.0f)
                        arcToRelative(8.0f, 8.0f, 0.0f, true, true, 16.0f, 0.0f)
                        arcTo(8.0f, 8.0f, 0.0f, false, true, 0.0f, 8.0f)
                        close()
                        moveTo(8.0f, 1.0f)
                        arcToRelative(7.0f, 7.0f, 0.0f, false, false, -5.468f, 11.37f)
                        curveTo(3.242f, 11.226f, 4.805f, 10.0f, 8.0f, 10.0f)
                        reflectiveCurveToRelative(4.757f, 1.225f, 5.468f, 2.37f)
                        arcTo(7.0f, 7.0f, 0.0f, false, false, 8.0f, 1.0f)
                        close()
                    }
                }
                .build()
        return _personplaceholder!!
    }

private var _personplaceholder: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.PersonPlaceholder, contentDescription = null)
    }
}
