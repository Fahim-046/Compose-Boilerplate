package com.fahimdev.data.repository

import com.fahimdev.core.network.ApiResult
import com.fahimdev.core.network.SafeApiRequest
import com.fahimdev.data.datasource.remote.MovieApiService
import com.fahimdev.data.mapper.MovieMapper
import com.fahimdev.domain.entities.Movie
import com.fahimdev.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val characterApiService: MovieApiService
) : MovieRepository {

    override suspend fun getMovies(page: Int, limit: Int): List<Movie> {
        return when (val result = SafeApiRequest.apiRequest { characterApiService.getMovies(page, limit) }) {
            is ApiResult.Success -> {
                result.data?.movies?.map { movieResponse ->
                    MovieMapper.mapResponseToDomain(movieResponse)
                } ?: emptyList()
            }
            is ApiResult.Error -> throw Exception(result.message)
            is ApiResult.NetworkError -> throw Exception(result.message)
        }
    }

    override suspend fun getMovie(id: Int): Movie? {
        return when (val result = SafeApiRequest.apiRequest { characterApiService.getMovies(id) }) {
            is ApiResult.Success -> {
                result.data?.movies?.map { movieResponse ->
                    MovieMapper.mapResponseToDomain(movieResponse)
                }?.firstOrNull()
            }
            is ApiResult.Error -> throw Exception(result.message)
            is ApiResult.NetworkError -> throw Exception(result.message)
        }
    }
}