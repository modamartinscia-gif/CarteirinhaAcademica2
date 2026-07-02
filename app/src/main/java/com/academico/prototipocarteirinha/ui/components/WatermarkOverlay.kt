package com.academico.prototipocarteirinha.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb

private const val TEXTO_MARCA_DAGUA = "PROTOTIPO ACADEMICO — SEM VALIDADE LEGAL"

@Composable
fun WatermarkOverlay(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val paint = android.graphics.Paint().apply {
            color = Color.Red.copy(alpha = 0.55f).toArgb()
            textSize = 34f
            isAntiAlias = true
            typeface = android.graphics.Typeface.DEFAULT_BOLD
        }

        rotate(degrees = -25f) {
            val stepY = 80f
            var y = -size.height
            while (y < size.height * 2) {
                drawContext.canvas.nativeCanvas.drawText(
                    TEXTO_MARCA_DAGUA,
                    -size.width,
                    y,
                    paint
                )
                drawContext.canvas.nativeCanvas.drawText(
                    TEXTO_MARCA_DAGUA,
                    size.width * 0.4f,
                    y,
                    paint
                )
                y += stepY
            }
        }
    }
}
