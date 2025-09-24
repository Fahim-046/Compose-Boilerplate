pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        maven(url = uri("https://androidx.dev/snapshots/builds/13508953/artifacts/repository"))
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

rootProject.name = "ComposeBoilerplate"
include(":app")
include(":core")
include(":domain")
include(":data")
include(":shared")
include(":iosApp")
include(":composeApp-desktop")
include(":composeApp-web")
