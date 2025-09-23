package com.fahimdev.composeboilerplate.presentation.movie.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahimdev.composeboilerplate.presentation.base.BaseScreen
import com.fahimdev.composeboilerplate.presentation.movie.list.components.HorizontalCarousel
import com.fahimdev.composeboilerplate.presentation.movie.list.components.MovieSlide
import com.fahimdev.composeboilerplate.ui.components.topbar.PrimaryTopBar
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import com.fahimdev.domain.entities.Movie

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val states by viewModel.states.collectAsState()
    MovieListSkeleton(movies = states.movies)
}

@Composable
fun MovieListSkeleton(modifier: Modifier = Modifier, movies: List<Movie?>) {
    BaseScreen(title = "CinemaHub", showTopBar = true, showBackArrow = false, topBar = {
        PrimaryTopBar(
            title = "CinemaHub",
            description = "Discover the best movies"
        )
    }) {
        Column {
            HorizontalCarousel(movies = movies.take(4))
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun MovieListScreenPreview() {
    ComposeBoilerplateTheme {
        MovieListSkeleton(movies = emptyList())
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun MovieListScreenPreviewDark() {
    ComposeBoilerplateTheme {
        MovieListSkeleton(movies = emptyList())
    }
}

