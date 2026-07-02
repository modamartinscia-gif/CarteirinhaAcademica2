package com.academico.prototipocarteirinha.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.academico.prototipocarteirinha.data.CardData

/**
 * PROTOTIPO ACADEMICO — Área de configurações/desenvolvedor (item 7 do escopo).
 *
 * Finalidade EXCLUSIVAMENTE didática: demonstrar, para quem está avaliando o
 * protótipo, como os dados de teste (curso, instituição fictícia, validade)
 * podem ser ajustados por trás de uma tela protegida por senha simples.
 *
 * Esta tela NÃO esconde nem desativa a marca d'água da carteirinha (ver
 * WatermarkOverlay.kt) — não existe, em nenhum lugar do código, uma opção
 * para remover esse aviso. A senha aqui serve apenas para simular o
 * conceito de "área restrita", conforme pedido no escopo do projeto,
 * e deve ser tratada como valor de demonstração (não use este esquema em
 * um app real; utilize autenticação apropriada).
 */
private const val SENHA_DEMONSTRACAO = "academico2026"

@Composable
fun SettingsScreen(
    dadosAtuais: CardData,
    onVoltar: () -> Unit,
    onSalvarDadosDeTeste: (curso: String, instituicao: String, validade: String) -> Unit
) {
    var autenticado by remember { mutableStateOf(false) }
    var senhaDigitada by remember { mutableStateOf("") }
    var erroSenha by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configurações de desenvolvedor") },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            if (!autenticado) {
                Text(
                    "Área de demonstração protegida por senha. " +
                        "Use apenas para ajustar dados de teste do protótipo.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = senhaDigitada,
                    onValueChange = {
                        senhaDigitada = it
                        erroSenha = false
                    },
                    label = { Text("Senha de demonstração") },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = erroSenha,
                    supportingText = {
                        if (erroSenha) Text("Senha incorreta.")
                        else Text("Senha padrão de demonstração: academico2026")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = {
                    if (senhaDigitada == SENHA_DEMONSTRACAO) {
                        autenticado = true
                    } else {
                        erroSenha = true
                    }
                }) {
                    Text("Entrar")
                }
            } else {
                var curso by remember { mutableStateOf(dadosAtuais.curso) }
                var instituicao by remember { mutableStateOf(dadosAtuais.instituicaoFicticia) }
                var validade by remember { mutableStateOf(dadosAtuais.dataValidade) }

                Text(
                    "Ajuste aqui os dados de teste padrão do protótipo. " +
                        "A marca d'água da carteirinha não é afetada por estas opções.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = curso,
                    onValueChange = { curso = it },
                    label = { Text("Curso padrão (teste)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = instituicao,
                    onValueChange = { instituicao = it },
                    label = { Text("Instituição fictícia padrão") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = validade,
                    onValueChange = { validade = it },
                    label = { Text("Validade padrão (teste)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = { onSalvarDadosDeTeste(curso, instituicao, validade) }) {
                    Text("Salvar dados de teste")
                }
            }
        }
    }
}
