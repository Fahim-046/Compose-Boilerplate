package com.fahimdev.composeboilerplate.presentation.movie.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import com.fahimdev.composeboilerplate.presentation.base.BaseScreen
import com.fahimdev.composeboilerplate.presentation.movie.list.components.HorizontalCarousel
import com.fahimdev.composeboilerplate.presentation.movie.list.components.MovieGrid
import com.fahimdev.composeboilerplate.presentation.movie.list.components.MovieHeader
import com.fahimdev.composeboilerplate.ui.components.shimmer.MovieHeaderShimmer
import com.fahimdev.composeboilerplate.ui.components.shimmer.MovieTileShimmer
import com.fahimdev.composeboilerplate.ui.components.shimmer.ShimmerEffect
import com.fahimdev.composeboilerplate.ui.components.topbar.PrimaryTopBar
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import com.fahimdev.domain.entities.Movie

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    onViewAllClick: (CategoryType) -> Unit,
    navBackStack: NavBackStack? = null,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val states by viewModel.states.collectAsState()
    MovieListSkeleton(
        trendingMovies = states.trendingMovies,
        popularMovies = states.popularMovies,
        upcomingMovies = states.upcomingMovies,
        isLoading = states.isLoading,
        navBackStack = navBackStack,
        onViewAllClick = {
            onViewAllClick(it)
        }
    )
}

@Composable
fun MovieListSkeleton(
    modifier: Modifier = Modifier,
    trendingMovies: List<Movie?>,
    popularMovies: List<Movie?>,
    upcomingMovies: List<Movie?>,
    isLoading: Boolean,
    navBackStack: NavBackStack? = null,
    onViewAllClick: (CategoryType) -> Unit
) {
    BaseScreen(
        title = "CinemaHub",
        showTopBar = true,
        showBackArrow = false,
        showBottomNavigation = navBackStack != null,
        navBackStack = navBackStack,
        topBar = {
            PrimaryTopBar(
                title = "CinemaHub",
                description = "Discover the best movies",
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(key = "carousel") {
                Column {
                    if (isLoading || trendingMovies.isEmpty()) {
                        ShimmerEffect(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp)
                        )
                    } else {
                        HorizontalCarousel(movies = trendingMovies)
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
                    MovieHeader(header = "Trending Now", actionText = "View All") {
                        onViewAllClick(CategoryType.Trending)
                    }
                }

                item(key = "trending_grid") {
                    MovieGrid(movies = trendingMovies)
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
                    MovieHeader(header = "Popular", actionText = "View All") {
                        onViewAllClick(CategoryType.Popular)
                    }
                }

                item(key = "popular_grid") {
                    MovieGrid(movies = popularMovies)
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
                    MovieHeader(header = CategoryType.Upcoming.toName(), actionText = "View All") {
                        onViewAllClick(CategoryType.Upcoming)
                    }
                }

                item(key = "upcoming_grid") {
                    MovieGrid(movies = upcomingMovies)
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
            isLoading = false,
            onViewAllClick = {}
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun MovieListScreenPreviewDark() {
    ComposeBoilerplateTheme(darkTheme = true) {
        MovieListSkeleton(
            trendingMovies = emptyList(),
            popularMovies = emptyList(),
            upcomingMovies = emptyList(),
            isLoading = false,
            onViewAllClick = {}
        )
    }
}



