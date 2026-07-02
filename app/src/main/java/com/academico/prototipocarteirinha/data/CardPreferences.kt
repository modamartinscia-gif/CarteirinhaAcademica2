package com.academico.prototipocarteirinha.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore local — nenhum dado sai do dispositivo (item 10 do escopo).
private val Context.dataStore by preferencesDataStore(name = "carteirinha_academica_prefs")

/**
 * Repositorio simples de persistencia LOCAL para o protótipo acadêmico.
 * Nao ha nenhuma sincronizacao remota, API, backend ou upload de dados.
 */
class CardPreferences(private val context: Context) {

    private object Keys {
        val NOME = stringPreferencesKey("nome_estudante")
        val FOTO_URI = stringPreferencesKey("foto_uri")
        val CURSO = stringPreferencesKey("curso")
        val INSTITUICAO = stringPreferencesKey("instituicao_ficticia")
        val MATRICULA = stringPreferencesKey("matricula_ficticia")
        val VALIDADE = stringPreferencesKey("data_validade")
    }

    val cardDataFlow: Flow<CardData> = context.dataStore.data.map { prefs ->
        CardData(
            nomeEstudante = prefs[Keys.NOME] ?: "",
            fotoUri = prefs[Keys.FOTO_URI],
            curso = prefs[Keys.CURSO] ?: "",
            instituicaoFicticia = prefs[Keys.INSTITUICAO] ?: "Instituto Ficticio de Ensino Superior",
            matriculaFicticia = prefs[Keys.MATRICULA] ?: "",
            dataValidade = prefs[Keys.VALIDADE] ?: ""
        )
    }

    suspend fun salvarCarteirinha(dados: CardData) {
        context.dataStore.edit { prefs ->
            prefs[Keys.NOME] = dados.nomeEstudante
            dados.fotoUri?.let { prefs[Keys.FOTO_URI] = it }
            prefs[Keys.CURSO] = dados.curso
            prefs[Keys.INSTITUICAO] = dados.instituicaoFicticia
            prefs[Keys.MATRICULA] = dados.matriculaFicticia
            prefs[Keys.VALIDADE] = dados.dataValidade
        }
    }

    /** Usado apenas pela tela de Configuracoes para ajustar dados de teste. */
    suspend fun atualizarDadosDeTeste(curso: String, instituicao: String, validade: String) {
        context.dataStore.edit { prefs ->
            prefs[Keys.CURSO] = curso
            prefs[Keys.INSTITUICAO] = instituicao
            prefs[Keys.VALIDADE] = validade
        }
    }
}
