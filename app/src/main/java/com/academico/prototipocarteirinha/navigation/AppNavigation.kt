package com.academico.prototipocarteirinha.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.academico.prototipocarteirinha.data.CardData
import com.academico.prototipocarteirinha.data.CardPreferences
import com.academico.prototipocarteirinha.ui.screens.CardScreen
import com.academico.prototipocarteirinha.ui.screens.FormScreen
import com.academico.prototipocarteirinha.ui.screens.HomeScreen
import com.academico.prototipocarteirinha.ui.screens.SettingsScreen
import kotlinx.coroutines.launch

// Rotas do protótipo acadêmico: Home, Formulário, Carteirinha e Configurações.
private object Rotas {
    const val HOME = "home"
    const val FORMULARIO = "formulario"
    const val CARTEIRINHA = "carteirinha"
    const val CONFIGURACOES = "configuracoes"
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    val context = LocalContext.current
    val preferencias = remember_preferences(context)
    val dados by preferencias.cardDataFlow.collectAsState(initial = CardData())
    val escopo = androidx.compose.runtime.rememberCoroutineScope()

    NavHost(navController = navController, startDestination = Rotas.HOME) {
        composable(Rotas.HOME) {
            HomeScreen(
                onCriarCarteirinha = { navController.navigate(Rotas.FORMULARIO) },
                onAbrirConfiguracoes = { navController.navigate(Rotas.CONFIGURACOES) }
            )
        }
        composable(Rotas.FORMULARIO) {
            FormScreen(
                dadosIniciais = dados,
                onVoltar = { navController.popBackStack() },
                onGerarCarteirinha = { novosDados ->
                    escopo.launch {
                        preferencias.salvarCarteirinha(novosDados)
                        navController.navigate(Rotas.CARTEIRINHA)
                    }
                }
            )
        }
        composable(Rotas.CARTEIRINHA) {
            CardScreen(
                dados = dados,
                onVoltar = { navController.popBackStack(Rotas.HOME, inclusive = false) }
            )
        }
        composable(Rotas.CONFIGURACOES) {
            SettingsScreen(
                dadosAtuais = dados,
                onVoltar = { navController.popBackStack() },
                onSalvarDadosDeTeste = { curso, instituicao, validade ->
                    escopo.launch {
                        preferencias.atualizarDadosDeTeste(curso, instituicao, validade)
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}

@Composable
private fun remember_preferences(context: android.content.Context): CardPreferences {
    return androidx.compose.runtime.remember { CardPreferences(context) }
}
