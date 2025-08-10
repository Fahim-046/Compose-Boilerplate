package com.fahimdev.composeboilerplate.presentation.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahimdev.domain.entities.Movie
import com.fahimdev.domain.usecase.GetMovieByIdUseCase
import com.fahimdev.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getMovieByIdUseCase: GetMovieByIdUseCase
): ViewModel() {
    private val _movieList = MutableStateFlow<List<Movie>>(emptyList())
    val movieList get() = _movieList

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie get() = _movie

    fun getMovies(page: Int, limit: Int = 10) = viewModelScope.launch{
        val movies = getMovieListUseCase(page, limit)

        if(movies.isNotEmpty()){
            _movieList.value = movies
        }
    }

    fun getMovieById(id: Int) = viewModelScope.launch {
        val movie = getMovieByIdUseCase(id)

        if(movie != null){
            _movie.value = movie
        }
    }
}