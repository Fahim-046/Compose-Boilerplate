package com.fahimdev.composeboilerplate.presentation.movie.list

import androidx.lifecycle.viewModelScope
import com.fahimdev.composeboilerplate.presentation.base.BaseViewModel
import com.fahimdev.composeboilerplate.presentation.movie.list.states.MovieListStates
import com.fahimdev.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase
) : BaseViewModel() {
    val states = MutableStateFlow(MovieListStates())

    init {
        loadMovieList()
    }

    private fun loadMovieList() = viewModelScope.launch {
        states.value = states.value.copy(isLoading = true)
        val movies = getMovieListUseCase.invoke()

        if (movies.isEmpty()) return@launch

        val trendingMovies = movies.filter {
            (it?.rating ?: 0.0) > 8.0 && (it?.year ?: 0) > Calendar.getInstance().get(Calendar.YEAR)
        }

        val popularMovies = movies.filter {
            (it?.rating ?: 0.0) > 7.0
        }

        states.value = states.value.copy(
            movies = movies,
            trendingMovies = trendingMovies,
            popularMovies = popularMovies,
            isLoading = false
        )
    }
}