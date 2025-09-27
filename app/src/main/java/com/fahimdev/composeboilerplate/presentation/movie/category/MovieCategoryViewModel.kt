package com.fahimdev.composeboilerplate.presentation.movie.category

import androidx.lifecycle.viewModelScope
import com.fahimdev.composeboilerplate.presentation.base.BaseViewModel
import com.fahimdev.composeboilerplate.presentation.movie.category.states.CategoryStates
import com.fahimdev.composeboilerplate.presentation.movie.list.CategoryType
import com.fahimdev.domain.usecase.GetPopularMovieListUseCase
import com.fahimdev.domain.usecase.GetTrendingMovieListUseCase
import com.fahimdev.domain.usecase.GetUpcomingMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieCategoryViewModel @Inject constructor(
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val getTrendingMovieListUseCase: GetTrendingMovieListUseCase,
    private val getUpcomingMovieListUseCase: GetUpcomingMovieListUseCase,
) : BaseViewModel() {
    val states = MutableStateFlow(CategoryStates())

    fun getMovies(type: CategoryType) = viewModelScope.launch {
        when (type) {
            CategoryType.Popular -> {
                states.value = states.value.copy(isLoading = true)
                val movies = getPopularMovieListUseCase.invoke()

                if (movies.isNotEmpty()) {
                    states.value = states.value.copy(isLoading = false, movies = movies)
                }
            }

            CategoryType.Trending -> {
                states.value = states.value.copy(isLoading = true)
                val movies = getTrendingMovieListUseCase.invoke()

                if (movies.isNotEmpty()) {
                    states.value = states.value.copy(isLoading = false, movies = movies)
                }
            }

            CategoryType.Upcoming -> {
                states.value = states.value.copy(isLoading = true)
                val movies = getUpcomingMovieListUseCase.invoke()

                if (movies.isNotEmpty()) {
                    states.value = states.value.copy(isLoading = false, movies = movies)
                }
            }

        }
    }
}