plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

// -----------------------------------------------------------------------
// PROTOTIPO ACADEMICO
// Este aplicativo simula, apenas para fins didaticos, a interface de uma
// carteirinha estudantil. Nenhuma carteirinha gerada tem validade legal e
// nenhum dado e enviado a servidores externos (tudo fica no dispositivo).
// -----------------------------------------------------------------------
android {
    namespace = "com.academico.prototipocarteirinha"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.academico.prototipocarteirinha"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0-academico"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("io.coil-kt:coil-compose:2.6.0")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
