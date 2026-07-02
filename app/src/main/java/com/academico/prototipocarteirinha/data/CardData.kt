package com.academico.prototipocarteirinha.data

/**
 * PROTOTIPO ACADEMICO — modelo de dados da carteirinha SIMULADA.
 *
 * Nenhum campo aqui representa um documento oficial. O objetivo e didatico:
 * demonstrar formularios, estado e persistencia local em Jetpack Compose.
 *
 * Os campos "curso", "instituicaoFicticia" e "dataValidade" possuem valores
 * de teste que podem ser ajustados na tela de Configuracoes (protegida por
 * senha) apenas para fins de demonstracao do funcionamento do protótipo —
 * a marca d'agua da carteirinha (ver WatermarkOverlay.kt) e fixa e NAO e
 * afetada por essas configuracoes.
 */
data class CardData(
    val nomeEstudante: String = "",
    val fotoUri: String? = null,
    val curso: String = "",
    val instituicaoFicticia: String = "Instituto Ficticio de Ensino Superior",
    val matriculaFicticia: String = "",
    val dataValidade: String = ""
)
