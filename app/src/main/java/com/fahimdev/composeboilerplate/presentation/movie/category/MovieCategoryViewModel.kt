package com.fahimdev.composeboilerplate.presentation.movie.category

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.fahimdev.composeboilerplate.presentation.base.BaseViewModel
import com.fahimdev.composeboilerplate.presentation.movie.category.states.CategoryStates
import com.fahimdev.composeboilerplate.presentation.movie.list.CategoryType
import com.fahimdev.data.datasource.remote.paging.MovieCategoryPagingSource
import com.fahimdev.domain.repository.MovieRepository
import com.fahimdev.domain.usecase.GetPopularMovieListUseCase
import com.fahimdev.domain.usecase.GetTrendingMovieListUseCase
import com.fahimdev.domain.usecase.GetUpcomingMovieListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MovieCategoryViewModel(
    private val movieRepository: MovieRepository
) : BaseViewModel() {
    val states = MutableStateFlow(CategoryStates())

    fun getMoviesPaging(type: CategoryType) = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            val category = when (type) {
                CategoryType.Popular -> "popular"
                CategoryType.Trending -> "trending"
                CategoryType.Upcoming -> "upcoming"
            }
            MovieCategoryPagingSource(
                category = category,
                movieRepository = movieRepository
            )
        }
    ).flow.cachedIn(viewModelScope)
}