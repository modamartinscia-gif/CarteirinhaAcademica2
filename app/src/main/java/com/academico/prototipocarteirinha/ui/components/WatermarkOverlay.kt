package com.academico.prototipocarteirinha.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas

/**
 * PROTOTIPO ACADEMICO — Marca d'agua OBRIGATORIA e FIXA.
 *
 * Este componente e desenhado por cima de QUALQUER carteirinha gerada pelo
 * app, de forma diagonal e repetida, cobrindo toda a area do cartao.
 *
 * IMPORTANTE (design intencional):
 * - Este texto e uma constante de codigo, NAO um valor configuravel.
 * - A tela de Configuracoes (SettingsScreen) NAO possui nenhuma opcao para
 *   ocultar, remover, redimensionar ou tornar esta marca d'agua transparente.
 * - Ela existe justamente para impedir que a simulacao seja confundida com
 *   um documento real, atendendo ao requisito do protótipo academico.
 */
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
