package com.fahimdev.composeboilerplate.presentation.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import com.fahimdev.composeboilerplate.presentation.base.BaseScreen
import com.fahimdev.composeboilerplate.presentation.settings.components.AppearanceTheme
import com.fahimdev.composeboilerplate.presentation.settings.components.LanguageAndAppearance
import com.fahimdev.composeboilerplate.presentation.settings.components.Languages
import com.fahimdev.composeboilerplate.presentation.settings.components.ProfileInformation
import com.fahimdev.composeboilerplate.ui.components.topbar.PrimaryTopBar
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme

@Composable
fun SettingsScreen(
    onLanguageSelected: (Languages) -> Unit = {},
    onAppearanceSelected: (AppearanceTheme) -> Unit = {},
    navBackStack: NavBackStack? = null,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    var selectedLanguage by remember { mutableStateOf(Languages.ENGLISH) }
    val selectedAppearance by viewModel.isDarkModeEnabled.collectAsState()
    SettingsScreenSkeleton(
        isDarkTheme = selectedAppearance == AppearanceTheme.DARK,
        selectedLanguage = selectedLanguage,
        selectedAppearance = selectedAppearance,
        navBackStack = navBackStack,
        onLanguageSelected = {
            selectedLanguage = it
        },
        onAppearanceSelected = { theme ->
            onAppearanceSelected(theme)
            viewModel.onThemeChanged(theme == AppearanceTheme.DARK)
        })
}

@Composable
fun SettingsScreenSkeleton(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    selectedLanguage: Languages,
    selectedAppearance: AppearanceTheme,
    navBackStack: NavBackStack? = null,
    onLanguageSelected: (Languages) -> Unit = {},
    onAppearanceSelected: (AppearanceTheme) -> Unit = {}
) {
    BaseScreen(
        title = "Settings",
        showTopBar = true,
        showBackArrow = false,
        showBottomNavigation = navBackStack != null,
        navBackStack = navBackStack,
        topBar = {
            PrimaryTopBar(
                title = "Settings",
                description = "Manage your personal information"
            )
        }) {
        Column(
            modifier = modifier
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            ProfileInformation()
            Spacer(modifier = Modifier.height(16.dp))
            LanguageAndAppearance(
                isDarkTheme = isDarkTheme,
                selectedLanguage = selectedLanguage,
                selectedAppearance = selectedAppearance,
                onLanguageSelected = { language ->
                    onLanguageSelected(language)
                },
                onAppearanceSelected = { appearanceTheme ->
                    onAppearanceSelected(appearanceTheme)
                }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreviewLight() {
    ComposeBoilerplateTheme {
        SettingsScreenSkeleton(
            selectedLanguage = Languages.ENGLISH,
            selectedAppearance = AppearanceTheme.LIGHT,
            isDarkTheme = false
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun SettingsScreenPreviewDark() {
    ComposeBoilerplateTheme {
        SettingsScreenSkeleton(
            selectedLanguage = Languages.ENGLISH,
            selectedAppearance = AppearanceTheme.LIGHT,
            isDarkTheme = true
        )
    }
}