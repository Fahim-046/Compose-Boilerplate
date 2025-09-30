package com.fahimdev.data.repository

import com.fahimdev.data.datasource.remote.apiService.MovieApiService
import com.fahimdev.data.mapper.MovieMapper
import com.fahimdev.domain.entities.Movie
import com.fahimdev.domain.entities.PaginatedResult
import com.fahimdev.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService
) : MovieRepository {
    override suspend fun getTrendingMovies(): List<Movie?> = withContext(Dispatchers.IO) {
        try {
            val response = movieApiService.getTrendingMovies()
            response.results.map(MovieMapper::mapResponseToDomain)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getUpcomingMovies(): List<Movie?> = withContext(Dispatchers.IO){
        try {
            val response = movieApiService.getUpcomingMovies()
            response.results.map(MovieMapper::mapResponseToDomain)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getPopularMovies(): List<Movie?> {
        return try {
            val response = movieApiService.getPopularMovies()
            response.results.map(MovieMapper::mapResponseToDomain)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getMoviesByCategory(category: String, page: Int): List<Movie?> = withContext(Dispatchers.IO) {
        try {
            val response = when (category) {
                "popular" -> movieApiService.getPopularMovies(page)
                "upcoming" -> movieApiService.getUpcomingMovies(page)
                "trending" -> movieApiService.getTrendingMovies(page)
                else -> throw IllegalArgumentException("Invalid category: $category")
            }
            response.results.map(MovieMapper::mapResponseToDomain)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getMoviesByCategoryPaginated(category: String, page: Int): PaginatedResult<Movie> = withContext(Dispatchers.IO) {
        val response = when (category) {
            "popular" -> movieApiService.getPopularMovies(page)
            "upcoming" -> movieApiService.getUpcomingMovies(page)
            "trending" -> movieApiService.getTrendingMovies(page)
            else -> throw IllegalArgumentException("Invalid category: $category")
        }

        val movies = response.results.mapNotNull(MovieMapper::mapResponseToDomain)

        PaginatedResult(
            data = movies,
            currentPage = response.page,
            totalPages = response.total_pages,
            hasNextPage = response.page < response.total_pages
        )
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