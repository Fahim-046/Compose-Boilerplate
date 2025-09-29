package com.fahimdev.composeboilerplate.presentation.movie.list.components

import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fahimdev.domain.entities.Movie

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
            .height(350.dp)
    ) {
        HorizontalPager(
            state = pageState,
            modifier = Modifier.fillMaxSize(),
            key = { index -> movies[index]?.id ?: index }
        ) { index ->
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