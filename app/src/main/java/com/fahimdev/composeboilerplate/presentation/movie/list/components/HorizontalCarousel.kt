package com.fahimdev.composeboilerplate.presentation.movie.list.components

import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fahimdev.domain.entities.Movie
import kotlinx.coroutines.delay

@Composable
fun HorizontalCarousel(
    modifier: Modifier = Modifier,
    movies: List<Movie?>,
    autoSlideDelay: Long = 3000L,
    indicatorColor: Int = Color.WHITE,
    activeIndicatorColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary
) {
    val pageState = rememberPagerState(pageCount = { movies.size })

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        HorizontalPager(state = pageState, modifier = Modifier.fillMaxSize()) { index ->
            MovieSlide(movie = movies[index])
        }

        Indicators(
            totalDots = movies.size,
            selectedIndex = pageState.currentPage,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun HorizontalCarouselPreview() {
    val movies = listOf(
        Movie(
            id = 1,
            title = "Movie Title",
            coverImage = "https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg", // TMDB poster
            year = 2018,
            rating = 8.0,
            genres = listOf("Action", "Adventure", "Comedy"),
            runtime = 200,
            summary = "It is a movie of someone.",
            imdbCode = "8CV65X4"
        ),
        Movie(
            id = 1,
            title = "Movie Title",
            coverImage = "https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg", // TMDB poster
            year = 2018,
            rating = 8.0,
            genres = listOf("Action", "Adventure", "Comedy"),
            runtime = 200,
            summary = "It is a movie of someone.",
            imdbCode = "8CV65X4"
        ),
        Movie(
            id = 1,
            title = "Movie Title",
            coverImage = "https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg", // TMDB poster
            year = 2018,
            rating = 8.0,
            genres = listOf("Action", "Adventure", "Comedy"),
            runtime = 200,
            summary = "It is a movie of someone.",
            imdbCode = "8CV65X4"
        )
    )
    HorizontalCarousel(movies = movies)
}