package com.fahimdev.composeboilerplate.presentation.movie.details.events

sealed class MovieDetailsEvents {
    data class GetMovieDetails(val id: Int) : MovieDetailsEvents()
}