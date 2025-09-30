package com.fahimdev.composeboilerplate.presentation.movie.category.components

import androidx.paging.PagingData
import com.fahimdev.domain.entities.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

fun <T : Any> List<T>.asTestPagingItems(): Flow<PagingData<T>> {
    return flowOf(PagingData.from(this))
}

object MoviePreviewData {
    val sampleMovies = listOf(
        Movie(
            id = 1,
            title = "The Shawshank Redemption",
            year = 1994,
            rating = 8.7,
            coverImage = "https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg",
            genres = listOf("Action", "Adventure", "Comedy"),
            runtime = 142,
            summary = "",
            imdbCode = "tt0111161"
        ),
        Movie(
            id = 2,
            title = "The Godfather",
            year = 1972,
            rating = 8.7,
            coverImage = "https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg",
            genres = listOf("Action", "Adventure", "Comedy"),
            runtime = 142,
            summary = "",
            imdbCode = "tt0111161"
        ),
        Movie(
            id = 3,
            title = "The Dark Knight",
            year = 2008,
            rating = 8.5,
            coverImage = "https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg",
            genres = listOf("Action", "Adventure", "Comedy"),
            runtime = 142,
            summary = "",
            imdbCode = "tt0111161"
        ),
        Movie(
            id = 4,
            title = "Pulp Fiction",
            year = 1994,
            rating = 8.5,
            coverImage = "https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg",
            genres = listOf("Action", "Adventure", "Comedy"),
            runtime = 142,
            summary = "",
            imdbCode = "tt0111161"
        ),
        Movie(
            id = 5,
            title = "Forrest Gump",
            year = 1994,
            rating = 8.5,
            coverImage = "https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg",
            genres = listOf("Action", "Adventure", "Comedy"),
            runtime = 142,
            summary = "",
            imdbCode = "tt0111161"
        ),
        Movie(
            id = 6,
            title = "Inception",
            year = 2010,
            rating = 8.5,
            coverImage = "https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg",
            genres = listOf("Action", "Adventure", "Comedy"),
            runtime = 142,
            summary = "",
            imdbCode = "tt0111161"
        )
    )
}
