package com.fahimdev.domain.repository

import com.fahimdev.domain.entities.Movie

interface MovieRepository {
    suspend fun getTrendingMovies(): List<Movie?>
    suspend fun getUpcomingMovies(): List<Movie?>
    suspend fun getPopularMovies(): List<Movie?>
    suspend fun getMovieById(id: Int): Movie
    suspend fun getMovieByPage(page: Int): List<Movie>
    suspend fun searchMovie(query: String): List<Movie>
}