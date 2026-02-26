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
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.compose.design_system.icons.AppIcons

public val AppIcons.SettingsPrivacyPolicy: ImageVector
    get() {
        if (_settingsprivacypolicy != null) {
            return _settingsprivacypolicy!!
        }
        _settingsprivacypolicy =
            Builder(
                    name = "SettingsPrivacyPolicy",
                    defaultWidth = 800.0.dp,
                    defaultHeight = 800.0.dp,
                    viewportWidth = 192.0f,
                    viewportHeight = 192.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 12.0f,
                        strokeLineCap = Round,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(151.8f, 144.5f)
                        arcToRelative(74.0f, 74.0f, 0.0f, false, true, -85.59f, 19.21f)
                        arcTo(74.0f, 74.0f, 0.0f, false, true, 22.42f, 87.7f)
                        arcToRelative(74.0f, 74.0f, 0.0f, false, true, 59.55f, -64.42f)
                        moveToRelative(28.03f, 0.06f)
                        arcToRelative(74.0f, 74.0f, 0.0f, false, true, 50.06f, 35.61f)
                        arcToRelative(74.0f, 74.0f, 0.0f, false, true, 5.91f, 61.15f)
                    }
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 12.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(76.0f, 92.0f)
                        horizontalLineToRelative(40.0f)
                        curveToRelative(4.43f, 0.0f, 8.0f, 3.57f, 8.0f, 8.0f)
                        verticalLineToRelative(22.0f)
                        curveToRelative(0.0f, 4.43f, -3.57f, 8.0f, -8.0f, 8.0f)
                        lineTo(76.0f, 130.0f)
                        curveToRelative(-4.43f, 0.0f, -8.0f, -3.57f, -8.0f, -8.0f)
                        verticalLineToRelative(-22.0f)
                        curveToRelative(0.0f, -4.43f, 3.57f, -8.0f, 8.0f, -8.0f)
                        close()
                        moveTo(80.0f, 92.0f)
                        lineTo(80.0f, 77.7f)
                        curveTo(80.0f, 69.03f, 87.16f, 62.0f, 96.0f, 62.0f)
                        reflectiveCurveToRelative(16.0f, 7.03f, 16.0f, 15.7f)
                        lineTo(112.0f, 92.0f)
                    }
                }
                .build()
        return _settingsprivacypolicy!!
    }

private var _settingsprivacypolicy: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.SettingsPrivacyPolicy, contentDescription = null)
    }
}
