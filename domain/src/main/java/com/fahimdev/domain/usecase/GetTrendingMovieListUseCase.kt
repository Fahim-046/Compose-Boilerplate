package com.fahimdev.domain.usecase

import com.fahimdev.domain.repository.MovieRepository
import javax.inject.Inject

class GetTrendingMovieListUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun invoke() = movieRepository.getTrendingMovies()
}