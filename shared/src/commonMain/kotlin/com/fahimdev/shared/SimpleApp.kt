package com.fahimdev.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fahimdev.shared.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var currentScreen by remember { mutableStateOf("Home") }
    val platform = getPlatform()

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Compose Multiplatform",
                            fontWeight = FontWeight.Medium
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                        label = { Text("Home") },
                        selected = currentScreen == "Home",
                        onClick = { currentScreen = "Home" }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Star, contentDescription = null) },
                        label = { Text("Movies") },
                        selected = currentScreen == "Movies",
                        onClick = { currentScreen = "Movies" }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Settings, contentDescription = null) },
                        label = { Text("Settings") },
                        selected = currentScreen == "Settings",
                        onClick = { currentScreen = "Settings" }
                    )
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                when (currentScreen) {
                    "Home" -> HomeScreen(platform.name)
                    "Movies" -> MoviesScreen()
                    "Settings" -> SettingsScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(platformName: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            "🚀 Compose Multiplatform",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            "Running on: $platformName",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "✅ Shared UI Working!",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "Your clean architecture is preserved:\n• Domain Layer\n• Data Layer\n• Core Layer\n• Presentation Layer",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Button(
            onClick = { /* TODO: Add functionality */ }
        ) {
            Icon(Icons.Filled.PlayArrow, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Get Started")
        }
    }
}

@Composable
fun MoviesScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            "🎬 Movies Screen",
            style = MaterialTheme.typography.headlineMedium
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Movies from Domain Layer",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "Connect this to your MovieRepository and GetMovieListUseCase",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun SettingsScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            "⚙️ Settings Screen",
            style = MaterialTheme.typography.headlineMedium
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Theme & Preferences",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "Dark/Light mode toggle",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}