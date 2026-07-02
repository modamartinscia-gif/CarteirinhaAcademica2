# Protótipo Acadêmico — Carteirinha Estudantil Fictícia

Este é um projeto Android (Kotlin + Jetpack Compose) desenvolvido **exclusivamente
para fins educacionais**, com o objetivo de demonstrar habilidades de
desenvolvimento mobile: formulários, upload/seleção de imagem, navegação entre
telas, renderização de layout e persistência local de dados.

## Aviso importante

Nenhuma carteirinha gerada por este aplicativo tem validade legal ou serve
como documento de identificação real. Todas as carteirinhas exibem, de forma
fixa e não removível, a marca d'água **"PROTÓTIPO ACADÊMICO — SEM VALIDADE
LEGAL"** (veja `ui/components/WatermarkOverlay.kt`). O app não gera QR Code
verificável, assinatura digital ou qualquer selo de autenticação, e não usa
nomes/logos de instituições reais.

## Como abrir o projeto

1. Abra o Android Studio (Koala ou mais recente).
2. `File > Open` e selecione a pasta `CarteirinhaAcademica`.
3. Aguarde a sincronização do Gradle (o Android Studio pode gerar
   automaticamente o `gradlew`/wrapper se necessário).
4. Rode em um emulador ou dispositivo com Android 7.0 (API 24) ou superior.

## Estrutura

- `MainActivity.kt` — ponto de entrada.
- `navigation/AppNavigation.kt` — rotas: Home, Formulário, Carteirinha, Configurações.
- `ui/screens/HomeScreen.kt` — tela inicial com botão "Criar carteirinha de teste".
- `ui/screens/FormScreen.kt` — formulário (nome, foto 3x4, curso, instituição
  fictícia, matrícula fictícia, validade).
- `ui/screens/CardScreen.kt` — renderização visual da carteirinha simulada.
- `ui/components/WatermarkOverlay.kt` — marca d'água obrigatória, sempre
  aplicada, sem opção de remoção em nenhuma tela.
- `ui/screens/SettingsScreen.kt` — área de configurações/desenvolvedor
  protegida por senha de demonstração (`academico2026`), usada apenas para
  ajustar valores de teste (curso, instituição fictícia, validade) e
  demonstrar o conceito de área restrita. Não afeta a marca d'água.
- `data/` — modelo de dados (`CardData`) e persistência local via DataStore
  (`CardPreferences`), sem qualquer comunicação com servidores externos.

## Dependências principais

Jetpack Compose, Navigation Compose, DataStore Preferences e Coil (para
exibir a imagem 3x4 selecionada localmente).
