package com.mygce.app.ui.brand

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mygce.app.ui.theme.BrandBlueDark
import com.mygce.app.ui.theme.BrandBlueLight
import com.mygce.app.ui.theme.BrandRedLight

/** The MyGCE app icon, drawn in code for use inside the app. Same geometry as ic_launcher_foreground.xml. */
@Composable
fun MyGceMark(modifier: Modifier = Modifier, size: Dp = 44.dp) {
    Canvas(modifier.size(size)) {
        val s = this.size.minDimension / 108f
        drawRoundRect(
            brush = Brush.linearGradient(listOf(BrandBlueLight, BrandBlueDark)),
            cornerRadius = CornerRadius(26f * s),
        )
        withTransform({ scale(1.12f, 1.12f, Offset(54f * s, 54f * s)) }) {
            withTransform({ rotate(10f, Offset(58f * s, 52f * s)) }) {
                drawRoundRect(
                    color = BrandRedLight,
                    topLeft = Offset(40f * s, 33f * s),
                    size = Size(36f * s, 36f * s),
                    cornerRadius = CornerRadius(9f * s),
                )
            }
            withTransform({ rotate(-8f, Offset(50f * s, 56f * s)) }) {
                drawRoundRect(
                    color = Color.White,
                    topLeft = Offset(30f * s, 36f * s),
                    size = Size(40f * s, 42f * s),
                    cornerRadius = CornerRadius(10f * s),
                )
                val tick = Path().apply {
                    moveTo(40f * s, 57.5f * s)
                    lineTo(47f * s, 64.5f * s)
                    lineTo(61f * s, 48f * s)
                }
                drawPath(
                    path = tick,
                    color = BrandBlueDark,
                    style = Stroke(width = 6.5f * s, cap = StrokeCap.Round, join = StrokeJoin.Round),
                )
            }
        }
    }
}
