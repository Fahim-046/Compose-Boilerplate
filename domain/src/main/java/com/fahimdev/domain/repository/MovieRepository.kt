package com.fahimdev.domain.repository

import com.fahimdev.domain.entities.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie?>
    suspend fun getMovieById(id: Int): Movie
    suspend fun getMovieByPage(page: Int): List<Movie>
    suspend fun searchMovie(query: String): List<Movie>
}