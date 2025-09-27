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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getTrendingMovieListUseCase: GetTrendingMovieListUseCase,
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val getUpcomingMovieListUseCase: GetUpcomingMovieListUseCase
) : BaseViewModel() {
    val states = MutableStateFlow(MovieListStates())

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
        states.value = states.value.copy(isLoading = true, error = null)

        try {
            val trendingDeferred = async { getTrendingMovieListUseCase.invoke() }
            val popularDeferred = async { getPopularMovieListUseCase.invoke() }
            val upcomingDeferred = async { getUpcomingMovieListUseCase.invoke() }

            val trending = trendingDeferred.await()
            val popular = popularDeferred.await()
            val upcoming = upcomingDeferred.await()

            states.value = MovieListStates(
                isLoading = false,
                trendingMovies = trending,
                popularMovies = popular,
                upcomingMovies = upcoming
            )
        } catch (e: Exception) {
            states.value = states.value.copy(
                isLoading = false,
                error = e.message ?: "Unknown error"
            )
        }
    }
}