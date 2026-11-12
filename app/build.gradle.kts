plugins {
    alias(libs.plugins.android.application)
    // No kotlin-android plugin: AGP 9 compiles Kotlin itself (built-in Kotlin). Same setup as Sipwise.
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.mygce.app"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.mygce.app"
        // Android 8.0: covers the cheap phones students use. DECISIONS/0001 [verify against target devices].
        minSdk = 26
        targetSdk = 35
        versionCode = 3
        versionName = "0.3.0-p3"
    }

    buildTypes {
        debug {
            // Test builds get their own app ID so they never clash with a future release install.
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    lint {
        abortOnError = true
        checkDependencies = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit)
}
