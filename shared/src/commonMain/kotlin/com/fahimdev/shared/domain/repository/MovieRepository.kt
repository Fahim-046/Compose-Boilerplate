package com.fahimdev.shared.domain.repository

import com.fahimdev.shared.domain.entities.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie?>
    suspend fun getMovieById(id: Int): Movie
    suspend fun getMovieByPage(page: Int): List<Movie>
    suspend fun searchMovie(query: String): List<Movie>
}