package com.fahimdev.domain.repository

import com.fahimdev.domain.entities.Movie

interface MovieRepository {
    suspend fun getMovies(page: Int, limit: Int): List<Movie>

    suspend fun getMovie(id: Int): Movie?
}