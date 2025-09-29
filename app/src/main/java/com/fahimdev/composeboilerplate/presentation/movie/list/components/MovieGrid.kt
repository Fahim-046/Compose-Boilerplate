package com.fahimdev.composeboilerplate.presentation.movie.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import com.fahimdev.domain.entities.Movie

@Composable
fun MovieGrid(
    movies: List<Movie?>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        movies.chunked(2).forEach { rowMovies ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowMovies.forEach { movie ->
                    MovieTile(
                        movie = movie,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowMovies.size == 1) {
                    Box(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieGridPreview() {
    ComposeBoilerplateTheme {
        MovieGrid(movies = listOf())
    }
}

@Preview
@Composable
private fun MovieGridPreviewDark() {
    ComposeBoilerplateTheme(darkTheme = true) {
        MovieGrid(movies = listOf())
    }
}