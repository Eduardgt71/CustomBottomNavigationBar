package com.group.custombottomnavigationbar.drawable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal val HomeIcon: ImageVector
    get() {
        if (_HomeIconSilhouetteSvgrepoCom != null) {
            return _HomeIconSilhouetteSvgrepoCom!!
        }
        _HomeIconSilhouetteSvgrepoCom = ImageVector.Builder(
            name = "HomeIconSilhouetteSvgrepoCom",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 495.4f,
            viewportHeight = 495.4f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(487.08f, 225.51f)
                lineToRelative(-75.08f, -75.08f)
                verticalLineTo(63.7f)
                curveToRelative(0f, -15.68f, -12.71f, -28.39f, -28.41f, -28.39f)
                curveToRelative(-15.67f, 0f, -28.38f, 12.71f, -28.38f, 28.39f)
                verticalLineToRelative(29.94f)
                lineTo(299.31f, 37.74f)
                curveToRelative(-27.64f, -27.62f, -75.69f, -27.58f, -103.27f, 0.05f)
                lineTo(8.31f, 225.51f)
                curveToRelative(-11.08f, 11.1f, -11.08f, 29.07f, 0f, 40.16f)
                curveToRelative(11.09f, 11.1f, 29.09f, 11.1f, 40.17f, 0f)
                lineToRelative(187.71f, -187.73f)
                curveToRelative(6.11f, -6.08f, 16.89f, -6.08f, 22.98f, -0.02f)
                lineToRelative(187.74f, 187.75f)
                curveToRelative(5.57f, 5.55f, 12.82f, 8.31f, 20.08f, 8.31f)
                curveToRelative(7.27f, 0f, 14.54f, -2.76f, 20.09f, -8.31f)
                curveTo(498.17f, 254.59f, 498.17f, 236.62f, 487.08f, 225.51f)
                close()
            }
            path(fill = SolidColor(Color.Black)) {
                moveTo(257.56f, 131.84f)
                curveToRelative(-5.45f, -5.45f, -14.28f, -5.45f, -19.72f, 0f)
                lineTo(72.71f, 296.91f)
                curveToRelative(-2.61f, 2.61f, -4.09f, 6.16f, -4.09f, 9.88f)
                verticalLineToRelative(120.4f)
                curveToRelative(0f, 28.25f, 22.91f, 51.16f, 51.16f, 51.16f)
                horizontalLineToRelative(81.75f)
                verticalLineToRelative(-126.61f)
                horizontalLineToRelative(92.3f)
                verticalLineToRelative(126.61f)
                horizontalLineToRelative(81.75f)
                curveToRelative(28.25f, 0f, 51.16f, -22.91f, 51.16f, -51.16f)
                verticalLineTo(306.79f)
                curveToRelative(0f, -3.71f, -1.47f, -7.27f, -4.09f, -9.88f)
                lineTo(257.56f, 131.84f)
                close()
            }
        }.build()

        return _HomeIconSilhouetteSvgrepoCom!!
    }

@Suppress("ObjectPropertyName")
private var _HomeIconSilhouetteSvgrepoCom: ImageVector? = null
