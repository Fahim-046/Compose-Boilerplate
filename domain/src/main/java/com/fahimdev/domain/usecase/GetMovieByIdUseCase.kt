package com.fahimdev.domain.usecase

import com.fahimdev.domain.repository.MovieRepository

class GetMovieByIdUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(id: Int) = movieRepository.getMovie(id)
}