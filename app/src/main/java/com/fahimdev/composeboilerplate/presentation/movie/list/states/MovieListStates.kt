package com.fahimdev.composeboilerplate.presentation.movie.list.states

import com.fahimdev.domain.entities.Movie

data class MovieListStates(
    val isLoading: Boolean = false,
    val movies: List<Movie?> = emptyList(),
    val trendingMovies: List<Movie?> = emptyList(),
    val popularMovies: List<Movie?> = emptyList(),
    val comingSoonMovies: List<Movie?> = emptyList(),
)
