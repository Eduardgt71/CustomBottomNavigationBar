package com.group.bottomview.drawable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val OutlineZephyr: ImageVector
    get() {
        if (_OutlineZephyr != null) {
            return _OutlineZephyr!!
        }
        _OutlineZephyr = ImageVector.Builder(
            name = "OutlineZephyr",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                //  fill = SolidColor(Color.Black),
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 80f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(287f, 758f)
                quadTo(273f, 747f, 260f, 736.5f)
                quadTo(247f, 726f, 235f, 714f)
                quadTo(227f, 717f, 219.5f, 718.5f)
                quadTo(212f, 720f, 203f, 720f)
                quadTo(170f, 720f, 146.5f, 696.5f)
                quadTo(123f, 673f, 123f, 640f)
                quadTo(123f, 620f, 131.5f, 603.5f)
                quadTo(140f, 587f, 155f, 576f)
                quadTo(147f, 553f, 144f, 529.5f)
                quadTo(141f, 506f, 141f, 480f)
                quadTo(141f, 454f, 144f, 430.5f)
                quadTo(147f, 407f, 155f, 384f)
                quadTo(140f, 373f, 131.5f, 356.5f)
                quadTo(123f, 340f, 123f, 320f)
                quadTo(123f, 287f, 146.5f, 263.5f)
                quadTo(170f, 240f, 203f, 240f)
                quadTo(212f, 240f, 219.5f, 241.5f)
                quadTo(227f, 243f, 235f, 246f)
                quadTo(268f, 210f, 310.5f, 186f)
                quadTo(353f, 162f, 401f, 150f)
                quadTo(406f, 120f, 428.5f, 100f)
                quadTo(451f, 80f, 481f, 80f)
                quadTo(511f, 80f, 533.5f, 100.5f)
                quadTo(556f, 121f, 561f, 150f)
                quadTo(609f, 162f, 651.5f, 185.5f)
                quadTo(694f, 209f, 727f, 244f)
                quadTo(735f, 241f, 742f, 239.5f)
                quadTo(749f, 238f, 757f, 238f)
                quadTo(790f, 238f, 813.5f, 261.5f)
                quadTo(837f, 285f, 837f, 318f)
                quadTo(837f, 338f, 829f, 353.5f)
                quadTo(821f, 369f, 807f, 380f)
                quadTo(815f, 404f, 818f, 429f)
                quadTo(821f, 454f, 821f, 480f)
                quadTo(821f, 506f, 818f, 530.5f)
                quadTo(815f, 555f, 807f, 578f)
                quadTo(821f, 589f, 829f, 604.5f)
                quadTo(837f, 620f, 837f, 640f)
                quadTo(837f, 673f, 813.5f, 696.5f)
                quadTo(790f, 720f, 757f, 720f)
                quadTo(749f, 720f, 742f, 718.5f)
                quadTo(735f, 717f, 727f, 714f)
                quadTo(715f, 726f, 702.5f, 737.5f)
                quadTo(690f, 749f, 675f, 760f)
                quadTo(601f, 798f, 588f, 802.5f)
                quadTo(575f, 807f, 561f, 810f)
                quadTo(556f, 839f, 533.5f, 859.5f)
                quadTo(511f, 880f, 481f, 880f)
                quadTo(451f, 880f, 428.5f, 860f)
                quadTo(406f, 840f, 401f, 810f)
                quadTo(386f, 807f, 372.5f, 802.5f)
                quadTo(359f, 798f, 345f, 792f)
                close()
            }
        }.build()

        return _OutlineZephyr!!
    }

@Suppress("ObjectPropertyName")
private var _OutlineZephyr: ImageVector? = null
