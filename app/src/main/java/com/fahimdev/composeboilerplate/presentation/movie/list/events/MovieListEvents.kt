package com.fahimdev.composeboilerplate.presentation.movie.list.events

sealed class MovieListEvents{
    data class OnViewAllClick(val type: String) : MovieListEvents()
    data class OnMovieClick(val id: Int) : MovieListEvents()
}