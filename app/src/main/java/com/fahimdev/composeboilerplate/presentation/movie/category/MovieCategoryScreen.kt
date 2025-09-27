package com.fahimdev.composeboilerplate.presentation.movie.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahimdev.composeboilerplate.presentation.base.BaseScreen
import com.fahimdev.composeboilerplate.presentation.movie.list.CategoryType
import com.fahimdev.composeboilerplate.presentation.movie.list.components.MovieTile
import com.fahimdev.composeboilerplate.presentation.movie.list.toName
import com.fahimdev.composeboilerplate.ui.components.topbar.PrimaryTopBar
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import com.fahimdev.domain.entities.Movie

@Composable
fun MovieCategoryScreen(
    modifier: Modifier = Modifier,
    type: CategoryType,
    onNavigateBack: () -> Unit,
    viewModel: MovieCategoryViewModel = hiltViewModel()
) {
    val states by viewModel.states.collectAsState()
    LaunchedEffect(key1 = type) {
        viewModel.getMovies(type)
    }

    MovieCategorySkeleton(
        modifier = modifier,
        type = type.toName(),
        movies = states.movies,
        onNavigateBack = onNavigateBack
    )
}

@Composable
fun MovieCategorySkeleton(
    modifier: Modifier = Modifier,
    type: String,
    movies: List<Movie?>,
    onNavigateBack: () -> Unit
) {
    BaseScreen(title = type, showTopBar = true, showBackArrow = true, topBar = {
        PrimaryTopBar(
            title = type,
            leadingIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onLeadingIconClick = onNavigateBack,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(movies) { movie ->
                    if (movie == null) return@items
                    MovieTile(movie = movie)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCategoryScreenPreview() {
    ComposeBoilerplateTheme {
        MovieCategorySkeleton(type = "Popular", onNavigateBack = {}, movies = emptyList())
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCategoryScreenPreviewDark() {
    ComposeBoilerplateTheme(darkTheme = true) {
        MovieCategorySkeleton(type = "Popular", onNavigateBack = {}, movies = emptyList())
    }
}