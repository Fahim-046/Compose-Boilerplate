package com.fahimdev.composeboilerplate.presentation.movie.list

import androidx.lifecycle.viewModelScope
import com.fahimdev.composeboilerplate.presentation.base.BaseViewModel
import com.fahimdev.composeboilerplate.presentation.movie.list.events.MovieListEvents
import com.fahimdev.composeboilerplate.presentation.movie.list.states.MovieListStates
import com.fahimdev.domain.usecase.GetPopularMovieListUseCase
import com.fahimdev.domain.usecase.GetTrendingMovieListUseCase
import com.fahimdev.domain.usecase.GetUpcomingMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getTrendingMovieListUseCase: GetTrendingMovieListUseCase,
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val getUpcomingMovieListUseCase: GetUpcomingMovieListUseCase
) : BaseViewModel() {
    private val _states = MutableStateFlow(MovieListStates())
    val states: StateFlow<MovieListStates> = _states.asStateFlow()

    init {
        loadMovieList()
    }

    fun onEvent(event: MovieListEvents){
        when(event){
            is MovieListEvents.OnMovieClick -> {

            }

            is MovieListEvents.OnViewAllClick -> {

            }
        }
    }

    private fun loadMovieList() = viewModelScope.launch {
        _states.value = _states.value.copy(isLoading = true, error = null)

        try {
            val trendingDeferred = async { getTrendingMovieListUseCase.invoke() }
            val popularDeferred = async { getPopularMovieListUseCase.invoke() }
            val upcomingDeferred = async { getUpcomingMovieListUseCase.invoke() }

            val trending = trendingDeferred.await()
            val popular = popularDeferred.await()
            val upcoming = upcomingDeferred.await()

            _states.value = MovieListStates(
                isLoading = false,
                trendingMovies = if(trending.size > 4) trending.take(4) else trending,
                popularMovies = if(popular.size > 4) popular.take(4) else popular,
                upcomingMovies = if(upcoming.size > 4) upcoming.take(4) else upcoming
            )
        } catch (e: Exception) {
            _states.value = _states.value.copy(
                isLoading = false,
                error = e.message ?: "Unknown error"
            )
        }
    }
}