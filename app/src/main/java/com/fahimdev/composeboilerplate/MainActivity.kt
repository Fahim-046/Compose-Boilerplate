package com.fahimdev.composeboilerplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fahimdev.composeboilerplate.presentation.naivgation.NavigationRoot
import com.fahimdev.composeboilerplate.presentation.settings.components.AppearanceTheme
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkTheme by viewModel.isDarkModeEnabled.collectAsState()
            val isLoggedIn by viewModel.isUserLoggedIn.collectAsState()

            val darkTheme = isDarkTheme
            val loggedIn = isLoggedIn

            if (loggedIn != null && darkTheme != null) {
                ComposeBoilerplateTheme(darkTheme = darkTheme) {
                    NavigationRoot(
                        isLoggedIn = loggedIn,
                        onLanguageChange = {

                        },
                        onAppearanceChange = {
                            viewModel.onThemeChanged(it == AppearanceTheme.DARK)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeBoilerplateTheme {
        Greeting("Android")
    }
}