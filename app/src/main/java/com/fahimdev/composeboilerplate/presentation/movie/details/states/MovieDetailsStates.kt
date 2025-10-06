package com.fahimdev.composeboilerplate.presentation.movie.details.states

import com.fahimdev.domain.entities.Movie

data class MovieDetailsStates(
    val movie: Movie? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
