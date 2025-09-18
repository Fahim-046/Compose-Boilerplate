import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

fun loadLocalProperties(): Properties {
    val properties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        FileInputStream(localPropertiesFile).use { fis ->
            properties.load(fis)
        }
    }
    return properties
}

val localProperties = loadLocalProperties()

android {
    namespace = "com.fahimdev.composeboilerplate"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.fahimdev.composeboilerplate"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "GOOGLE_WEB_CLIENT_ID", "\"${localProperties.getProperty("google.web.client.id", "")}\"")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "env"

    productFlavors {
        create("dev") {
            dimension = "env"
            applicationIdSuffix = ".dev"
            resValue("string", "api_base_url", localProperties.getProperty("api.base.url.dev", ""))
            resValue("string", "api_key", localProperties.getProperty("api.key.dev", ""))
            buildConfigField("String", "BASE_URL", "\"${localProperties.getProperty("api.base.url.dev", "")}\"")
            buildConfigField("String", "API_KEY", "\"${localProperties.getProperty("api.key.dev", "")}\"")

        }
        create("qa") {
            dimension = "env"
            applicationIdSuffix = ".qa"
            resValue("string", "api_base_url", localProperties.getProperty("api.base.url.qa", ""))
            resValue("string", "api_key", localProperties.getProperty("api.key.qa", ""))
            buildConfigField("String", "BASE_URL", "\"${localProperties.getProperty("api.base.url.qa", "")}\"")
            buildConfigField("String", "API_KEY", "\"${localProperties.getProperty("api.key.qa", "")}\"")

        }
        create("staging") {
            dimension = "env"
            applicationIdSuffix = ".staging"
            resValue("string", "api_base_url", localProperties.getProperty("api.base.url.staging", ""))
            resValue("string", "api_key", localProperties.getProperty("api.key.staging", ""))
            buildConfigField("String", "BASE_URL", "\"${localProperties.getProperty("api.base.url.staging", "")}\"")
            buildConfigField("String", "API_KEY", "\"${localProperties.getProperty("api.key.staging", "")}\"")

        }
        create("prod") {
            dimension = "env"
            resValue("string", "api_base_url", localProperties.getProperty("api.base.url.prod", ""))
            resValue("string", "api_key", localProperties.getProperty("api.key.prod", ""))
            buildConfigField("String", "BASE_URL", "\"${localProperties.getProperty("api.base.url.prod", "")}\"")
            buildConfigField("String", "API_KEY", "\"${localProperties.getProperty("api.key.prod", "")}\"")

        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))

    // AndroidX + Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.hilt.navigation.compose)

    // Navigation 3
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.kotlinx.serialization.core)

    // Retrofit + OkHttp
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp.logging.interceptor)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Coil
    implementation(libs.coil.compose)

    // Timber
    implementation(libs.timber)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
    useBuildCache = false
}