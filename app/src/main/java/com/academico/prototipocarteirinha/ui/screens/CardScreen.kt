package com.academico.prototipocarteirinha.ui.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.academico.prototipocarteirinha.R
import com.academico.prototipocarteirinha.data.CardData
import com.academico.prototipocarteirinha.ui.components.WatermarkOverlay
import com.academico.prototipocarteirinha.ui.theme.AzulAcademico
import com.academico.prototipocarteirinha.ui.theme.Dourado

/**
 * PROTOTIPO ACADEMICO — Tela que renderiza a carteirinha SIMULADA.
 *
 * A marca d'agua (WatermarkOverlay) e desenhada por cima do cartao de forma
 * incondicional — nao existe nenhum parametro, flag ou configuracao neste
 * arquivo capaz de omiti-la. Nenhum QR Code, assinatura digital ou selo de
 * autenticacao e renderizado (itens 4 e 6 do escopo do protótipo).
 */
@Composable
fun CardScreen(dados: CardData, onVoltar: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carteirinha (simulação)") },
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
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.watermark_text),
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.55f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(AzulAcademico, AzulAcademico.copy(alpha = 0.85f))
                        )
                    )
            ) {
                Column(modifier = Modifier.fillMaxSize().padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = dados.instituicaoFicticia.ifBlank { "Instituto Fictício de Ensino Superior" },
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                maxLines = 2
                            )
                            Text(
                                text = "Carteira de Identificação Estudantil (simulação)",
                                color = Color.White.copy(alpha = 0.75f),
                                fontSize = 10.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(RoundedCornerShape(50))
                                .background(Dourado)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        // Foto estilo 3x4
                        Box(
                            modifier = Modifier
                                .width(80.dp)
                                .height(104.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            if (dados.fotoUri != null) {
                                AsyncImage(
                                    model = Uri.parse(dados.fotoUri),
                                    contentDescription = "Foto 3x4 do estudante fictício",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Icon(
                                    Icons.Filled.Person,
                                    contentDescription = null,
                                    tint = Color.Gray,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            CampoCarteirinha(rotulo = "NOME", valor = dados.nomeEstudante.ifBlank { "—" })
                            CampoCarteirinha(rotulo = "CURSO", valor = dados.curso.ifBlank { "—" })
                            CampoCarteirinha(rotulo = "MATRÍCULA (fictícia)", valor = dados.matriculaFicticia.ifBlank { "—" })
                            CampoCarteirinha(rotulo = "VALIDADE", valor = dados.dataValidade.ifBlank { "—" })
                        }
                    }
                }

                // Marca d'agua fixa, sempre por cima de todo o conteudo do cartao.
                WatermarkOverlay(modifier = Modifier.matchParentSize())
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
                Text(
                    text = "Este cartão é uma simulação gerada para fins acadêmicos. " +
                        "Não é um documento oficial, não possui QR Code verificável, " +
                        "assinatura digital ou qualquer elemento de autenticação.",
                    modifier = Modifier.padding(14.dp),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun CampoCarteirinha(rotulo: String, valor: String) {
    Column(modifier = Modifier.padding(bottom = 6.dp)) {
        Text(text = rotulo, color = Color.White.copy(alpha = 0.65f), fontSize = 9.sp)
        Text(text = valor, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}
