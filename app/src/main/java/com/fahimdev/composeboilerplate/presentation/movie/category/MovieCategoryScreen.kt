package com.fahimdev.composeboilerplate.presentation.movie.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.fahimdev.composeboilerplate.presentation.base.BaseScreen
import com.fahimdev.composeboilerplate.presentation.movie.category.components.MoviePreviewData
import com.fahimdev.composeboilerplate.presentation.movie.category.components.asTestPagingItems
import com.fahimdev.composeboilerplate.presentation.movie.list.CategoryType
import com.fahimdev.composeboilerplate.presentation.movie.list.components.MovieTile
import com.fahimdev.composeboilerplate.presentation.movie.list.toName
import com.fahimdev.composeboilerplate.ui.components.shimmer.MovieTileShimmer
import com.fahimdev.composeboilerplate.ui.components.topbar.PrimaryTopBar
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import com.fahimdev.domain.entities.Movie
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieCategoryScreen(
    modifier: Modifier = Modifier,
    type: CategoryType,
    onNavigateBack: () -> Unit,
    viewModel: MovieCategoryViewModel = koinViewModel()
) {
    val moviesPagingItems = viewModel.getMoviesPaging(type).collectAsLazyPagingItems()

    MovieCategorySkeleton(
        modifier = modifier,
        type = type.toName(),
        movies = moviesPagingItems,
        onNavigateBack = onNavigateBack
    )
}

@Composable
fun MovieCategorySkeleton(
    modifier: Modifier = Modifier,
    type: String,
    movies: LazyPagingItems<Movie>,
    onNavigateBack: () -> Unit
) {
    BaseScreen(title = type, showTopBar = true, showBackArrow = true, topBar = {
        PrimaryTopBar(
            title = type,
            leadingIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onLeadingIconClick = onNavigateBack,
        )
    }) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            when (movies.loadState.refresh) {
                is LoadState.Loading -> {
                    MovieTileShimmer(size = 6)
                }

                is LoadState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.Text("Error loading movies")
                    }
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(movies.itemCount) { index ->
                            val movie = movies[index]
                            movie?.let {
                                MovieTile(movie = it)
                            }
                        }

                        if (movies.loadState.append is LoadState.Loading) {
                            item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(32.dp),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        if (movies.loadState.append is LoadState.Error) {
                            item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    androidx.compose.material3.Text(
                                        text = "Error loading more movies",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCategorySkeletonPreview() {
    ComposeBoilerplateTheme {
        val movies = MoviePreviewData.sampleMovies
            .asTestPagingItems()
            .collectAsLazyPagingItems(

            )
        MovieCategorySkeleton(
            type = "Popular",
            movies = movies,
            onNavigateBack = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCategorySkeletonPreviewDark() {
    ComposeBoilerplateTheme(darkTheme = true) {
        val movies = MoviePreviewData.sampleMovies
            .asTestPagingItems()
            .collectAsLazyPagingItems(

            )
        MovieCategorySkeleton(
            type = "Popular",
            movies = movies,
            onNavigateBack = {}
        )
    }
}