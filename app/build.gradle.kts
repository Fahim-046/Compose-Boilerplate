import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
    id("com.google.gms.google-services")
}

fun loadLocalProperties(): Properties {
    val properties = Properties()
    val localPropertiesFile = rootProject.file("app/local.properties")
    if (localPropertiesFile.exists()) {
        FileInputStream(localPropertiesFile).use { fis ->
            properties.load(fis)
        }
    }
    return properties
}

val localProperties = loadLocalProperties()

fun getApiProperty(key: String, flavor: String): String {
    // priority: environment variable > local.properties > default empty
    val envKey = "${key.uppercase().replace(".", "_")}_${flavor.uppercase()}" // e.g., API_BASE_URL_PROD
    return System.getenv(envKey)
        ?: localProperties.getProperty("$key.$flavor")
        ?: ""
}

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

        buildConfigField(
            "String",
            "GOOGLE_WEB_CLIENT_ID",
            "\"${localProperties.getProperty("google.web.client.id", "")}\""
        )
    }

    signingConfigs {
        create("release") {
            val keystoreEnv = System.getenv("KEYSTORE_FILE")
            val keystoreFile = if (keystoreEnv != null) file(keystoreEnv) else null

            if (keystoreFile != null) {
                storeFile = keystoreFile
                storePassword = System.getenv("KEYSTORE_PASSWORD")
                keyAlias = System.getenv("PROD_KEY_ALIAS")
                keyPassword = System.getenv("PROD_KEY_PASSWORD")
            } else {
                val keystorePath = localProperties.getProperty("keystore.file") ?: ""
                if (keystorePath.isNotEmpty()) {
                    storeFile = file(keystorePath)
                    storePassword = localProperties.getProperty("keystore.password")
                    keyAlias = localProperties.getProperty("prod.key.alias")
                    keyPassword = localProperties.getProperty("prod.key.password")
                } else {
                    println("⚠️ No keystore found. Release build may be unsigned locally.")
                }
            }
        }
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
            signingConfig = signingConfigs.getByName("release")
        }
    }

    flavorDimensions += "env"

    productFlavors {
        listOf("dev", "qa", "staging", "prod").forEach { flavor ->
            create(flavor) {
                dimension = "env"
                if (flavor != "prod") applicationIdSuffix = ".$flavor"

                val baseUrl = getApiProperty("api.base.url", flavor)
                val apiKey = getApiProperty("api.key", flavor)

                resValue("string", "api_base_url", baseUrl)
                resValue("string", "api_key", apiKey)

                buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
                buildConfigField("String", "API_KEY", "\"$apiKey\"")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions { jvmTarget = "11" }

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
    ksp(libs.hilt.compiler)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.navigation)

    // Coil
    implementation(libs.coil.compose)

    // Paging 3
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

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