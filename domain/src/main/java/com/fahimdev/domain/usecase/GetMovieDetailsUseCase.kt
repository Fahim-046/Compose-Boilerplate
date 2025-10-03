package com.fahimdev.domain.usecase

import com.fahimdev.domain.repository.MovieRepository

class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {
    suspend fun invoke(id: Int) = movieRepository.getMovieDetails(id)
}