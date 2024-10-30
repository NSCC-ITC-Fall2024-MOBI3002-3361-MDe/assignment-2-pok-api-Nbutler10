plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.pokemonapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pokemonapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true // Allow compose
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Compose version
    }

    kotlinOptions {
        jvmTarget = "1.8" // JVM kotlin version (min 1.8 allowed for my editor)
    }

}

dependencies {

    implementation(libs.ui) // UI
    implementation(libs.material3) // Material3
    implementation(libs.androidx.lifecycle.runtime.ktx.v261) // For ViewModel and Compose integration
    implementation(libs.androidx.lifecycle.viewmodel.compose) // Needed for compose in MainActivity
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)

    implementation(libs.coil.compose) // coil / compose for images
    implementation(libs.retrofit) // Retrofit
    implementation(libs.converter.gson)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
}
