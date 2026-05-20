package com.storium.ui.core.modifier

import android.graphics.BlurMaskFilter
import android.graphics.Paint as AndroidPaint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp

@Composable
actual fun Modifier.shadowGlow(
    color: Color,
    borderRadius: Dp,
    blurRadius: Dp,
    offsetX: Dp,
    offsetY: Dp,
    spread: Dp,
): Modifier = composed {
    this.then(
        Modifier.drawBehind {
            val spreadPx = spread.toPx()
            val blurRadiusPx = blurRadius.toPx()
            val offsetXPx = offsetX.toPx()
            val offsetYPx = offsetY.toPx()
            val borderRadiusPx = borderRadius.toPx()

            if (color.alpha == 0f && blurRadiusPx <= 0f && spreadPx == 0f && offsetXPx == 0f && offsetYPx == 0f) {
                return@drawBehind
            }

            val frameworkPaint = AndroidPaint().apply {
                isAntiAlias = true
                style = AndroidPaint.Style.FILL
                this.color = color.toArgb()
                if (blurRadiusPx > 0f) {
                    maskFilter = BlurMaskFilter(blurRadiusPx, BlurMaskFilter.Blur.NORMAL)
                }
            }

            val left = -spreadPx + offsetXPx
            val top = -spreadPx + offsetYPx
            val right = size.width + spreadPx + offsetXPx
            val bottom = size.height + spreadPx + offsetYPx

            drawIntoCanvas { canvas ->
                canvas.nativeCanvas.drawRoundRect(left, top, right, bottom, borderRadiusPx, borderRadiusPx, frameworkPaint)
            }
        }
    )
}
