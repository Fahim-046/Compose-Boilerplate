package com.fahimdev.composeboilerplate.presentation.naivgation

import androidx.navigation3.runtime.NavKey
import com.fahimdev.composeboilerplate.presentation.movie.list.CategoryType
import kotlinx.serialization.Serializable

sealed interface Screen : NavKey {
    @Serializable
    data object Splash : Screen

    @Serializable
    data object MovieList : Screen

    @Serializable
    data class MovieDetails(val id: Int) : Screen

    @Serializable
    data object Authentication : Screen

    @Serializable
    data object Settings : Screen

    @Serializable
    data class MovieCategory(val type: CategoryType) : Screen
}