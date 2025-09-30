package com.fahimdev.composeboilerplate.presentation.naivgation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.fahimdev.composeboilerplate.presentation.authentication.AuthenticationScreen
import com.fahimdev.composeboilerplate.presentation.authentication.AuthenticationViewModel
import com.fahimdev.composeboilerplate.presentation.movie.category.MovieCategoryScreen
import com.fahimdev.composeboilerplate.presentation.movie.category.MovieCategoryViewModel
import com.fahimdev.composeboilerplate.presentation.movie.list.MovieListScreen
import com.fahimdev.composeboilerplate.presentation.movie.list.MovieListViewModel
import com.fahimdev.composeboilerplate.presentation.settings.SettingsScreen
import com.fahimdev.composeboilerplate.presentation.settings.SettingsViewModel
import com.fahimdev.composeboilerplate.presentation.settings.components.AppearanceTheme
import com.fahimdev.composeboilerplate.presentation.settings.components.Languages
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean,
    onLanguageChange: (Languages) -> Unit,
    onAppearanceChange: (AppearanceTheme) -> Unit
) {
    val backStack =
        rememberNavBackStack(if (isLoggedIn) Screen.MovieList else Screen.Authentication)

    NavDisplay(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is Screen.Splash -> {
                    NavEntry(
                        key = key
                    ) {
                        MovieListScreen(
                            onViewAllClick = {
                                backStack.add(Screen.MovieCategory(it))
                            }
                        )
                    }
                }

                is Screen.Authentication -> {
                    NavEntry(
                        key = key
                    ) {
                        val viewModel: AuthenticationViewModel = koinViewModel()
                        AuthenticationScreen(
                            viewModel = viewModel,
                            onSignInSuccess = {
                                backStack.removeLastOrNull()
                                backStack.add(Screen.MovieList)
                            }
                        )
                    }
                }

                is Screen.MovieList -> {
                    NavEntry(
                        key = key
                    ) {
                        val viewModel: MovieListViewModel = koinViewModel()
                        MovieListScreen(
                            onViewAllClick = {
                                backStack.add(Screen.MovieCategory(it))
                            },
                            navBackStack = backStack,
                            viewModel = viewModel
                        )
                    }
                }

                is Screen.Settings -> {
                    NavEntry(
                        key = key
                    ) {
                        val viewModel: SettingsViewModel = koinViewModel()
                        SettingsScreen(
                            onAppearanceSelected = {
                                onAppearanceChange(it)
                            },
                            onLanguageSelected = {
                                onLanguageChange(it)
                            },
                            viewModel = viewModel,
                            navBackStack = backStack
                        )
                    }
                }

                is Screen.MovieCategory -> {
                    NavEntry(
                        key = key
                    ) {
                        val viewModel: MovieCategoryViewModel = koinViewModel()
                        MovieCategoryScreen(
                            type = key.type,
                            onNavigateBack = { backStack.removeLastOrNull() },
                            viewModel = viewModel
                        )
                    }
                }

                else -> throw RuntimeException("Invalid NavKey.")
            }
        }

    )
}