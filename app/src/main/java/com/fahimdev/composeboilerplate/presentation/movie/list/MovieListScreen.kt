package com.fahimdev.composeboilerplate.presentation.movie.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahimdev.composeboilerplate.presentation.base.BaseScreen
import com.fahimdev.composeboilerplate.presentation.movie.list.components.HorizontalCarousel
import com.fahimdev.composeboilerplate.presentation.movie.list.components.MovieHeader
import com.fahimdev.composeboilerplate.presentation.movie.list.components.MovieSlide
import com.fahimdev.composeboilerplate.presentation.movie.list.components.MovieTile
import com.fahimdev.composeboilerplate.ui.components.shimmer.MovieHeaderShimmer
import com.fahimdev.composeboilerplate.ui.components.shimmer.MovieTileShimmer
import com.fahimdev.composeboilerplate.ui.components.shimmer.ShimmerEffect
import com.fahimdev.composeboilerplate.ui.components.topbar.PrimaryTopBar
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import com.fahimdev.domain.entities.Movie

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val states by viewModel.states.collectAsState()
    MovieListSkeleton(
        trendingMovies = states.trendingMovies,
        popularMovies = states.popularMovies,
        upcomingMovies = states.upcomingMovies,
        isLoading = states.isLoading
    )
}

@Composable
fun MovieListSkeleton(
    modifier: Modifier = Modifier,
    trendingMovies: List<Movie?>,
    popularMovies: List<Movie?>,
    upcomingMovies: List<Movie?>,
    isLoading: Boolean
) {
    BaseScreen(
        title = "CinemaHub", showTopBar = true, showBackArrow = false,
        topBar = {
            PrimaryTopBar(
                title = "CinemaHub",
                description = "Discover the best movies",
            )
        },
    ) {
        val trending = if (trendingMovies.size > 4) trendingMovies.take(4) else trendingMovies
        val popular = if (popularMovies.size > 4) popularMovies.take(4) else popularMovies
        val upcoming = if (upcomingMovies.size > 4) upcomingMovies.take(4) else upcomingMovies

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column {
                    if (isLoading || trendingMovies.isEmpty()) {
                        ShimmerEffect(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp)
                        )
                    } else {
                        HorizontalCarousel(movies = trendingMovies.take(4))
                    }
                }
            }

            if (isLoading || trendingMovies.isEmpty()) {
                item {
                    MovieHeaderShimmer()
                }

                item {
                    MovieTileShimmer()
                }
            }

            if (trendingMovies.isNotEmpty()) {
                item {
                    MovieHeader(header = "Trending Now", actionText = "View All") { }
                }

                item {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .height(
                                (trending.size / 2 + trending.size % 2) * 320.dp +
                                        ((trending.size / 2 + trending.size % 2) - 1) * 16.dp
                            )
                            .padding(16.dp),
                        userScrollEnabled = false
                    ) {
                        items(trending) { movie ->
                            MovieTile(movie = movie)
                        }

                    }
                }
            }

            if (isLoading || popularMovies.isEmpty()) {
                item {
                    MovieHeaderShimmer()
                }

                item {
                    MovieTileShimmer()
                }
            }

            if (popularMovies.isNotEmpty()) {
                item {
                    MovieHeader(header = "Popular", actionText = "View All") { }
                }

                item {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .height(
                                (popular.size / 2 + popular.size % 2) * 320.dp +
                                        ((popular.size / 2 + popular.size % 2) - 1) * 16.dp
                            )
                            .padding(16.dp),
                        userScrollEnabled = false
                    ) {
                        items(popular) { movie ->
                            MovieTile(movie = movie)
                        }
                    }
                }
            }

            if (isLoading || upcomingMovies.isEmpty()) {
                item {
                    MovieHeaderShimmer()
                }

                item {
                    MovieTileShimmer()
                }
            }

            if (upcomingMovies.isNotEmpty()) {
                item {
                    MovieHeader(header = "Upcoming", actionText = "View All") { }
                }

                item {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .height(
                                (upcoming.size / 2 + upcoming.size % 2) * 320.dp +
                                        ((upcoming.size / 2 + upcoming.size % 2) - 1) * 16.dp
                            )
                            .padding(16.dp),
                        userScrollEnabled = false
                    ) {
                        items(upcoming) { movie ->
                            MovieTile(movie = movie)
                        }

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListScreenPreview() {
    ComposeBoilerplateTheme {
        MovieListSkeleton(
            trendingMovies = emptyList(),
            popularMovies = emptyList(),
            upcomingMovies = emptyList(),
            isLoading = false
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun MovieListScreenPreviewDark() {
    ComposeBoilerplateTheme {
        MovieListSkeleton(
            trendingMovies = emptyList(),
            popularMovies = emptyList(),
            upcomingMovies = emptyList(),
            isLoading = false
        )
    }
}

