package com.fahimdev.shared.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object Splash : Screen

    @Serializable
    data object Home : Screen

    @Serializable
    data object Authentication : Screen

    @Serializable
    data object Settings : Screen

    @Serializable
    data object Movies : Screen
}