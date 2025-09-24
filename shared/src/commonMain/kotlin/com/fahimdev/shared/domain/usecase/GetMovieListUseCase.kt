package com.fahimdev.shared.domain.usecase

import com.fahimdev.shared.domain.repository.MovieRepository

class GetMovieListUseCase(private val movieRepository: MovieRepository) {
    suspend fun invoke() = movieRepository.getMovies()
}