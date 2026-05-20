package com.storium.ui.core.modifier

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.skiaCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import org.jetbrains.skia.FilterBlurMode
import org.jetbrains.skia.MaskFilter
import org.jetbrains.skia.Paint as SkiaPaint

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

            val skiaPaint = SkiaPaint().apply {
                isAntiAlias = true
                this.color = color.toArgb()
                if (blurRadiusPx > 0f) {
                    maskFilter = MaskFilter.makeBlur(FilterBlurMode.NORMAL, blurRadiusPx / 2f)
                }
            }

            val left = -spreadPx + offsetXPx
            val top = -spreadPx + offsetYPx
            val right = size.width + spreadPx + offsetXPx
            val bottom = size.height + spreadPx + offsetYPx

            drawIntoCanvas { canvas ->
                canvas.skiaCanvas.drawRRect(
                    org.jetbrains.skia.RRect.makeLTRB(left, top, right, bottom, borderRadiusPx),
                    skiaPaint,
                )
            }
        }
    )
}
