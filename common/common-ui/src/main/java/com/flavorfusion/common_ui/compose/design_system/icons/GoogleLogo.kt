// @ImageVectorWizard:vectorXml=PHZlY3RvciB4bWxuczphbmRyb2lkPSJodHRwOi8vc2NoZW1hcy5hbmRyb2lkLmNvbS9hcGsvcmVzL2FuZHJvaWQiDQogICAgYW5kcm9pZDp3aWR0aD0iODAwZHAiDQogICAgYW5kcm9pZDpoZWlnaHQ9IjgwMGRwIg0KICAgIGFuZHJvaWQ6dmlld3BvcnRXaWR0aD0iNDgiDQogICAgYW5kcm9pZDp2aWV3cG9ydEhlaWdodD0iNDgiPg0KICA8cGF0aA0KICAgICAgYW5kcm9pZDpwYXRoRGF0YT0iTTEwLjMyNywyNGMwLC0xLjUyNCAwLjI1MywtMi45ODYgMC43MDUsLTQuMzU2bC03LjkwOSwtNi4wNEEyMy40NiwyMy40NiAwLDAgMCwwLjcxMyAyNGMwLDMuNzM3IDAuODY4LDcuMjYgMi40MDcsMTAuMzg4bDcuOTA1LC02LjA1QTEzLjksMTMuOSAwLDAgMSwxMC4zMjcgMjQiDQogICAgICBhbmRyb2lkOmZpbGxDb2xvcj0iI0ZCQkMwNSINCiAgICAgIGFuZHJvaWQ6ZmlsbFR5cGU9ImV2ZW5PZGQiLz4NCiAgPHBhdGgNCiAgICAgIGFuZHJvaWQ6cGF0aERhdGE9Ik0yNC4yMTQsMTAuMTMzYzMuMzExLDAgNi4zMDIsMS4xNzQgOC42NTIsMy4wOTRMMzkuNzAyLDYuNEMzNS41MzYsMi43NzMgMzAuMTk1LDAuNTMzIDI0LjIxNCwwLjUzM2EyMy40MywyMy40MyAwLDAgMCwtMjEuMDkgMTMuMDcxbDcuOTA4LDYuMDRhMTMuODUsMTMuODUgMCwwIDEsMTMuMTgyIC05LjUxIg0KICAgICAgYW5kcm9pZDpmaWxsQ29sb3I9IiNFQjQzMzUiDQogICAgICBhbmRyb2lkOmZpbGxUeXBlPSJldmVuT2RkIi8+DQogIDxwYXRoDQogICAgICBhbmRyb2lkOnBhdGhEYXRhPSJNMjQuMjE0LDM3Ljg2N2ExMy44NSwxMy44NSAwLDAgMSwtMTMuMTgyIC05LjUxbC03LjkwOSw2LjAzOGEyMy40MywyMy40MyAwLDAgMCwyMS4wOSAxMy4wNzJjNS43MzIsMCAxMS4yMDUsLTIuMDM2IDE1LjMxMiwtNS44NDlsLTcuNTA3LC01LjgwNGMtMi4xMTgsMS4zMzUgLTQuNzg2LDIuMDUzIC03LjgwNCwyLjA1MyINCiAgICAgIGFuZHJvaWQ6ZmlsbENvbG9yPSIjMzRBODUzIg0KICAgICAgYW5kcm9pZDpmaWxsVHlwZT0iZXZlbk9kZCIvPg0KICA8cGF0aA0KICAgICAgYW5kcm9pZDpwYXRoRGF0YT0iTTQ2LjY0NSwyNGMwLC0xLjM4NyAtMC4yMTMsLTIuODggLTAuNTM0LC00LjI2N0wyNC4yMTQsMTkuNzMzTDI0LjIxNCwyOC44aDEyLjYwNGMtMC42MywzLjA5MSAtMi4zNDYsNS40NjggLTQuOCw3LjAxNGw3LjUwNyw1LjgwNGM0LjMxNCwtNC4wMDQgNy4xMiwtOS45NjkgNy4xMiwtMTcuNjE4Ig0KICAgICAgYW5kcm9pZDpmaWxsQ29sb3I9IiM0Mjg1RjQiDQogICAgICBhbmRyb2lkOmZpbGxUeXBlPSJldmVuT2RkIi8+DQo8L3ZlY3Rvcj4NCg==
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

val AppIcons.GoogleLogo: ImageVector
    get() {
        if (_googleLogo != null) {
            return _googleLogo!!
        }
        _googleLogo =
            Builder(
                    name = "GoogleLogo",
                    defaultWidth = 40.0.dp,
                    defaultHeight = 40.0.dp,
                    viewportWidth = 48.0f,
                    viewportHeight = 48.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0xFFFBBC05)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd,
                    ) {
                        moveTo(10.327f, 24.0f)
                        curveToRelative(0.0f, -1.524f, 0.253f, -2.986f, 0.705f, -4.356f)
                        lineToRelative(-7.909f, -6.04f)
                        arcTo(23.46f, 23.46f, 0.0f, false, false, 0.713f, 24.0f)
                        curveToRelative(0.0f, 3.737f, 0.868f, 7.26f, 2.407f, 10.388f)
                        lineToRelative(7.905f, -6.05f)
                        arcTo(13.9f, 13.9f, 0.0f, false, true, 10.327f, 24.0f)
                    }
                    path(
                        fill = SolidColor(Color(0xFFEB4335)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd,
                    ) {
                        moveTo(24.214f, 10.133f)
                        curveToRelative(3.311f, 0.0f, 6.302f, 1.174f, 8.652f, 3.094f)
                        lineTo(39.702f, 6.4f)
                        curveTo(35.536f, 2.773f, 30.195f, 0.533f, 24.214f, 0.533f)
                        arcToRelative(23.43f, 23.43f, 0.0f, false, false, -21.09f, 13.071f)
                        lineToRelative(7.908f, 6.04f)
                        arcToRelative(13.85f, 13.85f, 0.0f, false, true, 13.182f, -9.51f)
                    }
                    path(
                        fill = SolidColor(Color(0xFF34A853)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd,
                    ) {
                        moveTo(24.214f, 37.867f)
                        arcToRelative(13.85f, 13.85f, 0.0f, false, true, -13.182f, -9.51f)
                        lineToRelative(-7.909f, 6.038f)
                        arcToRelative(23.43f, 23.43f, 0.0f, false, false, 21.09f, 13.072f)
                        curveToRelative(5.732f, 0.0f, 11.205f, -2.036f, 15.312f, -5.849f)
                        lineToRelative(-7.507f, -5.804f)
                        curveToRelative(-2.118f, 1.335f, -4.786f, 2.053f, -7.804f, 2.053f)
                    }
                    path(
                        fill = SolidColor(Color(0xFF4285F4)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd,
                    ) {
                        moveTo(46.645f, 24.0f)
                        curveToRelative(0.0f, -1.387f, -0.213f, -2.88f, -0.534f, -4.267f)
                        lineTo(24.214f, 19.733f)
                        lineTo(24.214f, 28.8f)
                        horizontalLineToRelative(12.604f)
                        curveToRelative(-0.63f, 3.091f, -2.346f, 5.468f, -4.8f, 7.014f)
                        lineToRelative(7.507f, 5.804f)
                        curveToRelative(4.314f, -4.004f, 7.12f, -9.969f, 7.12f, -17.618f)
                    }
                }
                .build()
        return _googleLogo!!
    }

private var _googleLogo: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.GoogleLogo, contentDescription = null)
    }
}
