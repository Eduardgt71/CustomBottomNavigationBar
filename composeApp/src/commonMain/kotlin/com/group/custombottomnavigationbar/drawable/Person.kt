package com.group.custombottomnavigationbar.drawable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal val Person: ImageVector
    get() {
        if (_PersonSvgrepoCom != null) {
            return _PersonSvgrepoCom!!
        }
        _PersonSvgrepoCom = ImageVector.Builder(
            name = "PersonSvgrepoCom",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(16f, 15.503f)
                arcTo(5.041f, 5.041f, 0f, isMoreThanHalf = true, isPositiveArc = false, 16f, 5.42f)
                arcToRelative(5.041f, 5.041f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 10.083f)
                close()
                moveTo(16f, 17.718f)
                curveToRelative(-6.703f, 0f, -11f, 3.699f, -11f, 5.5f)
                verticalLineToRelative(3.363f)
                horizontalLineToRelative(22f)
                verticalLineToRelative(-3.363f)
                curveToRelative(0f, -2.178f, -4.068f, -5.5f, -11f, -5.5f)
                close()
            }
        }.build()

        return _PersonSvgrepoCom!!
    }

@Suppress("ObjectPropertyName")
private var _PersonSvgrepoCom: ImageVector? = null
