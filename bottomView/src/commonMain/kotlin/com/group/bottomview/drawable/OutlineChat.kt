package com.group.bottomview.drawable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val OutlineChat: ImageVector
    get() {
        if (_OutlineChat != null) {
            return _OutlineChat!!
        }
        _OutlineChat = ImageVector.Builder(
            name = "OutlineChat",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f,
            autoMirror = true
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(240f, 560f)
                lineTo(560f, 560f)
                lineTo(560f, 480f)
                lineTo(240f, 480f)
                lineTo(240f, 560f)
                close()
                moveTo(240f, 440f)
                lineTo(720f, 440f)
                lineTo(720f, 360f)
                lineTo(240f, 360f)
                lineTo(240f, 440f)
                close()
                moveTo(240f, 320f)
                lineTo(720f, 320f)
                lineTo(720f, 240f)
                lineTo(240f, 240f)
                lineTo(240f, 320f)
                close()
                moveTo(80f, 880f)
                lineTo(80f, 160f)
                quadTo(80f, 127f, 103.5f, 103.5f)
                quadTo(127f, 80f, 160f, 80f)
                lineTo(800f, 80f)
                quadTo(833f, 80f, 856.5f, 103.5f)
                quadTo(880f, 127f, 880f, 160f)
                lineTo(880f, 640f)
                quadTo(880f, 673f, 856.5f, 696.5f)
                quadTo(833f, 720f, 800f, 720f)
                lineTo(240f, 720f)
                lineTo(80f, 880f)
                close()
                moveTo(206f, 640f)
                lineTo(800f, 640f)
                quadTo(800f, 640f, 800f, 640f)
                quadTo(800f, 640f, 800f, 640f)
                lineTo(800f, 160f)
                quadTo(800f, 160f, 800f, 160f)
                quadTo(800f, 160f, 800f, 160f)
                lineTo(160f, 160f)
                quadTo(160f, 160f, 160f, 160f)
                quadTo(160f, 160f, 160f, 160f)
                lineTo(160f, 685f)
                lineTo(206f, 640f)
                close()
                moveTo(160f, 640f)
                lineTo(160f, 640f)
                lineTo(160f, 160f)
                quadTo(160f, 160f, 160f, 160f)
                quadTo(160f, 160f, 160f, 160f)
                lineTo(160f, 160f)
                quadTo(160f, 160f, 160f, 160f)
                quadTo(160f, 160f, 160f, 160f)
                lineTo(160f, 640f)
                quadTo(160f, 640f, 160f, 640f)
                quadTo(160f, 640f, 160f, 640f)
                close()
            }
        }.build()

        return _OutlineChat!!
    }

@Suppress("ObjectPropertyName")
private var _OutlineChat: ImageVector? = null
