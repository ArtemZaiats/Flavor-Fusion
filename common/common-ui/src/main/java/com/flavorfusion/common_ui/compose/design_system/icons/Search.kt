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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

public val AppIcons.Search: ImageVector
    get() {
        if (_search != null) {
            return _search!!
        }
        _search =
            Builder(
                    name = "Search",
                    defaultWidth = 20.0.dp,
                    defaultHeight = 20.0.dp,
                    viewportWidth = 20.0f,
                    viewportHeight = 20.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0xFFAEB2B7)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(15.244f, 15.244f)
                        curveTo(15.57f, 14.919f, 16.097f, 14.919f, 16.423f, 15.244f)
                        lineTo(19.756f, 18.577f)
                        curveTo(20.081f, 18.903f, 20.081f, 19.43f, 19.756f, 19.756f)
                        curveTo(19.43f, 20.081f, 18.903f, 20.081f, 18.577f, 19.756f)
                        lineTo(15.244f, 16.423f)
                        curveTo(14.919f, 16.097f, 14.919f, 15.57f, 15.244f, 15.244f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFFAEB2B7)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd,
                    ) {
                        moveTo(8.333f, 0.0f)
                        curveTo(12.936f, 0.0f, 16.667f, 3.731f, 16.667f, 8.333f)
                        curveTo(16.667f, 12.936f, 12.936f, 16.667f, 8.333f, 16.667f)
                        curveTo(3.731f, 16.667f, 0.0f, 12.936f, 0.0f, 8.333f)
                        curveTo(0.0f, 3.731f, 3.731f, 0.0f, 8.333f, 0.0f)
                        close()
                        moveTo(8.333f, 1.667f)
                        curveTo(4.651f, 1.667f, 1.667f, 4.651f, 1.667f, 8.333f)
                        curveTo(1.667f, 12.015f, 4.651f, 15.0f, 8.333f, 15.0f)
                        curveTo(12.015f, 15.0f, 15.0f, 12.015f, 15.0f, 8.333f)
                        curveTo(15.0f, 4.651f, 12.015f, 1.667f, 8.333f, 1.667f)
                        close()
                    }
                }
                .build()
        return _search!!
    }

private var _search: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Search, contentDescription = null)
    }
}
