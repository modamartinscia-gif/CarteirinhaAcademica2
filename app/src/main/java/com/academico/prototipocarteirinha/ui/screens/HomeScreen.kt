package com.academico.prototipocarteirinha.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * PROTOTIPO ACADEMICO — Tela inicial.
 * Deixa explicito, ja na primeira tela, que se trata de uma simulacao
 * sem validade legal (item 4 do escopo).
 */
@Composable
fun HomeScreen(
    onCriarCarteirinha: () -> Unit,
    onAbrirConfiguracoes: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carteirinha Acadêmica") },
                actions = {
                    // Acesso discreto às configurações de desenvolvedor,
                    // protegido por senha na própria tela (item 7 do escopo).
                    IconButton(onClick = onAbrirConfiguracoes) {
                        Icon(Icons.Filled.Settings, contentDescription = "Configurações de desenvolvedor")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Protótipo Acadêmico",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Este aplicativo demonstra, apenas para fins educacionais, " +
                    "a interface de uma carteirinha estudantil fictícia. " +
                    "Nenhuma carteirinha gerada aqui possui validade legal ou serve como documento real.",
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = onCriarCarteirinha) {
                Text("Criar carteirinha de teste")
            }
        }
    }
}
