@file:OptIn(ExperimentalMaterial3Api::class)

package com.fahimdev.composeboilerplate.presentation.character.list

import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahimdev.composeboilerplate.presentation.movie.list.MovieListViewModel
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import com.fahimdev.domain.entities.Movie

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    MovieListSkeleton()
}

@Composable
fun MovieListSkeleton(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
private fun MovieListSkeletonPreview() {
    ComposeBoilerplateTheme {
        MovieListSkeleton()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MovieListSkeletonPreviewDark() {
    ComposeBoilerplateTheme {
        MovieListSkeleton()
    }
}