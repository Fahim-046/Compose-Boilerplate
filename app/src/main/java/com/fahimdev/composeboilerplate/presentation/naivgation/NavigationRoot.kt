package com.fahimdev.composeboilerplate.presentation.naivgation

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
import com.fahimdev.composeboilerplate.presentation.settings.SettingsScreen
import com.fahimdev.composeboilerplate.presentation.settings.SettingsViewModel
import com.fahimdev.composeboilerplate.presentation.settings.components.AppearanceTheme
import com.fahimdev.composeboilerplate.presentation.settings.components.Languages

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    onLanguageChange: (Languages) -> Unit,
    onAppearanceChange: (AppearanceTheme) -> Unit
) {
    val backStack = rememberNavBackStack(Screen.Authentication)
    NavDisplay(
        modifier = modifier,
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
                        AuthenticationScreen()
                    }
                }

                is Screen.Authentication -> {
                    NavEntry(
                        key = key
                    ) {
                        AuthenticationScreen()
                    }
                }

                is Screen.Settings -> {
                    NavEntry(
                        key = key
                    ) {
                        val viewModel: SettingsViewModel = hiltViewModel()
                        SettingsScreen(
                            onAppearanceSelected = {
                                onAppearanceChange(it)
                            },
                            onLanguageSelected = {
                                onLanguageChange(it)
                            },
                            viewModel = viewModel
                        )
                    }
                }

                else -> throw RuntimeException("Invalid NavKey.")
            }
        }

    )
}