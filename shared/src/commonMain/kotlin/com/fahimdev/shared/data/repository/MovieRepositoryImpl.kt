package com.fahimdev.shared.data.repository

import com.fahimdev.shared.data.datasource.remote.apiService.MovieApiService
import com.fahimdev.shared.data.mapper.MovieMapper
import com.fahimdev.shared.domain.entities.Movie
import com.fahimdev.shared.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val apiService: MovieApiService
) : MovieRepository {
    override suspend fun getMovies(): List<Movie?> = withContext(Dispatchers.Default) {
        val response = apiService.getMovies()
        response.map(MovieMapper::mapResponseToDomain)
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