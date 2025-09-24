package com.fahimdev.shared.presentation.movies

import com.fahimdev.domain.entities.Movie
import com.fahimdev.domain.usecase.GetMovieListUseCase
import com.fahimdev.shared.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getMoviesUseCase: GetMovieListUseCase
) : BaseViewModel() {

    private val _moviesState = MutableStateFlow(MoviesState())
    val moviesState = _moviesState.asStateFlow()

    init {
        loadMovies()
    }

    fun loadMovies() = viewModelScope.launch {
        _moviesState.value = _moviesState.value.copy(isLoading = true)

        try {
            val movies = getMoviesUseCase.invoke().filterNotNull()
            _moviesState.value = _moviesState.value.copy(
                movies = movies,
                isLoading = false
            )
        } catch (e: Exception) {
            _moviesState.value = _moviesState.value.copy(
                isLoading = false,
                error = e.message
            )
        }
    }
}

data class MoviesState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)