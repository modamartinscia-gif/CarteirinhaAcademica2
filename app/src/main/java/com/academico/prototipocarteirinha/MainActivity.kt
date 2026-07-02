package com.academico.prototipocarteirinha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.academico.prototipocarteirinha.navigation.AppNavigation
import com.academico.prototipocarteirinha.ui.theme.CarteirinhaAcademicaTheme

/**
 * PROTOTIPO ACADEMICO — Ponto de entrada do app.
 *
 * Este projeto tem finalidade exclusivamente didatica: demonstrar
 * desenvolvimento Android com Jetpack Compose (formularios, navegacao,
 * upload de imagem local e persistencia com DataStore). As carteirinhas
 * geradas NAO possuem validade legal e sempre exibem uma marca d'agua
 * fixa informando essa condicao (ver ui/components/WatermarkOverlay.kt).
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarteirinhaAcademicaTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppContent()
                }
            }
        }
    }
}

@Composable
private fun AppContent() {
    AppNavigation()
}
