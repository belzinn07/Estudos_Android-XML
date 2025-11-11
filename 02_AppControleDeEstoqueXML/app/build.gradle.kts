plugins {
    alias(libs.plugins.android.application)

}

android {
    namespace = "com.example.controledeestoque_xml"
    compileSdk = 36



    defaultConfig {
        applicationId = "com.example.controledeestoque_xml"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.lifecycle.viewmodel.android)
    // 1. Versão do Room corrigida para uma versão estável existente
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")



        implementation("androidx.recyclerview:recyclerview:1.4.0")
        // For control over item selection of both touch and mouse driven selection
        implementation("androidx.recyclerview:recyclerview-selection:1.2.0")

    // 2. Retrofit e Gson atualizados para a versão estável mais recente para garantir compatibilidade
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")




    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}