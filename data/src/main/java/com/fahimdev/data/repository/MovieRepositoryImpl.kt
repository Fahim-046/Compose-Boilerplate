package com.fahimdev.data.repository

import com.fahimdev.data.datasource.remote.apiService.MovieApiService
import com.fahimdev.data.mapper.MovieMapper
import com.fahimdev.data.model.MovieResponse
import com.fahimdev.domain.entities.Movie
import com.fahimdev.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService
) : MovieRepository {
    override suspend fun getMovies(): List<Movie?> = withContext(Dispatchers.IO) {
        val response = movieApiService.getMovies()
        response.results.map(MovieMapper::mapResponseToDomain)
    }

    override suspend fun getMovieById(id: Int): Movie {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieByPage(page: Int): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovie(query: String): List<Movie> {
        TODO("Not yet implemented")
    }

}