package com.fahimdev.composeboilerplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fahimdev.composeboilerplate.presentation.character.list.MovieListScreen
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBoilerplateTheme {
                MovieListScreen()
            }
        }
    }
}