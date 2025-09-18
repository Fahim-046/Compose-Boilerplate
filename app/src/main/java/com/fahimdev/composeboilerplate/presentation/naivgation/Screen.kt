package com.fahimdev.composeboilerplate.presentation.naivgation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Screen : NavKey {
    @Serializable
    data object Splash : Screen

    @Serializable
    data object Home : Screen

    @Serializable
    data object Authentication : Screen

    @Serializable
    data object Settings : Screen
}