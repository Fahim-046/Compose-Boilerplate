package com.fahimdev.composeboilerplate.presentation.movie.category.states

import com.fahimdev.domain.entities.Movie

data class CategoryStates(
    var isLoading: Boolean = false,
    var movies: List<Movie?> = emptyList(),
)