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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven(url = uri("https://androidx.dev/snapshots/builds/13508953/artifacts/repository"))
        mavenCentral()
    }
}

rootProject.name = "ComposeBoilerplate"
include(":app")
include(":core")
include(":domain")
include(":data")
