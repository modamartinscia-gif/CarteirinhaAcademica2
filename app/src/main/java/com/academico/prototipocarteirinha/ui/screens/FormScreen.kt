package com.academico.prototipocarteirinha.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.academico.prototipocarteirinha.data.CardData

/**
 * PROTOTIPO ACADEMICO — Formulario de preenchimento da carteirinha SIMULADA.
 *
 * A foto e escolhida via seletor de imagens do sistema (Storage Access
 * Framework / ActivityResultContracts.GetContent), sem necessidade de
 * permissoes de armazenamento em tempo de execucao.
 */
@Composable
fun FormScreen(
    dadosIniciais: CardData,
    onVoltar: () -> Unit,
    onGerarCarteirinha: (CardData) -> Unit
) {
    var nome by remember { mutableStateOf(dadosIniciais.nomeEstudante) }
    var fotoUri by remember { mutableStateOf(dadosIniciais.fotoUri) }
    var curso by remember { mutableStateOf(dadosIniciais.curso) }
    var instituicao by remember { mutableStateOf(dadosIniciais.instituicaoFicticia) }
    var matricula by remember { mutableStateOf(dadosIniciais.matriculaFicticia) }
    var validade by remember { mutableStateOf(dadosIniciais.dataValidade) }

    val seletorImagem = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { fotoUri = it.toString() }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dados de teste (fictícios)") },
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
            Text(
                text = "Preencha somente dados fictícios. Este formulário não coleta " +
                    "nem envia informações reais a nenhum servidor.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Foto estilo 3x4
            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 130.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFE5E7EB)),
                contentAlignment = Alignment.Center
            ) {
                if (fotoUri != null) {
                    AsyncImage(
                        model = Uri.parse(fotoUri),
                        contentDescription = "Foto 3x4 selecionada",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(Icons.Filled.AddAPhoto, contentDescription = null, tint = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(onClick = { seletorImagem.launch("image/*") }) {
                Text("Selecionar foto 3x4")
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome do estudante (fictício)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = curso,
                onValueChange = { curso = it },
                label = { Text("Curso (fictício)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = instituicao,
                onValueChange = { instituicao = it },
                label = { Text("Instituição fictícia") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = matricula,
                onValueChange = { matricula = it },
                label = { Text("Matrícula fictícia") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = validade,
                onValueChange = { validade = it },
                label = { Text("Data de validade (ex: 12/2026) — teste") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onGerarCarteirinha(
                        CardData(
                            nomeEstudante = nome,
                            fotoUri = fotoUri,
                            curso = curso,
                            instituicaoFicticia = instituicao,
                            matriculaFicticia = matricula,
                            dataValidade = validade
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Gerar carteirinha simulada")
            }
        }
    }
}
