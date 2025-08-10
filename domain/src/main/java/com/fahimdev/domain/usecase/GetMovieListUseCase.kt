package com.fahimdev.domain.usecase

import com.fahimdev.domain.repository.MovieRepository

class GetMovieListUseCase(private val movieRepository: MovieRepository){
    suspend operator fun invoke(page: Int, limit: Int) = movieRepository.getMovies(page, limit)
}