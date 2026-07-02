package com.academico.prototipocarteirinha.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = AzulAcademico,
    secondary = AzulClaro,
    tertiary = Dourado,
    background = CinzaFundo,
    error = VermelhoAlerta
)

private val DarkColors = darkColorScheme(
    primary = AzulClaro,
    secondary = AzulAcademico,
    tertiary = Dourado,
    error = VermelhoAlerta
)

@Composable
fun CarteirinhaAcademicaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}
