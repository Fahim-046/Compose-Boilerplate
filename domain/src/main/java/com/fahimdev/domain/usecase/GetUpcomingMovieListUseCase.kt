package com.fahimdev.domain.usecase

import com.fahimdev.domain.repository.MovieRepository
import javax.inject.Inject

class GetUpcomingMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun invoke() = movieRepository.getUpcomingMovies()
}