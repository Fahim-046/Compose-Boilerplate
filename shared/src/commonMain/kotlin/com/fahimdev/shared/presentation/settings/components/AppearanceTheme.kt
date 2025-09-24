package com.fahimdev.shared.presentation.settings.components

enum class AppearanceTheme {
    LIGHT,
    DARK,
    SYSTEM
}

fun AppearanceTheme.getName(): String {
    return when (this) {
        AppearanceTheme.LIGHT -> "Light"
        AppearanceTheme.DARK -> "Dark"
        AppearanceTheme.SYSTEM -> "System"
    }
}