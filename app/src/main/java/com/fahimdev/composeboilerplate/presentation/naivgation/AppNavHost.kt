//package com.fahimdev.composeboilerplate.presentation.naivgation
//
//import androidx.compose.runtime.Composable
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.fahimdev.composeboilerplate.presentation.settings.SettingsScreen
//import com.fahimdev.composeboilerplate.presentation.settings.SettingsViewModel
//import com.fahimdev.composeboilerplate.presentation.settings.components.AppearanceTheme
//import com.fahimdev.composeboilerplate.presentation.settings.components.Languages
//
//@Composable
//fun AppNavHost(
//    navHostController: NavHostController = rememberNavController(),
//    onAppearanceSelected: (AppearanceTheme) -> Unit,
//    onLanguageSelected: (Languages) -> Unit
//) {
//    NavHost(
//        navController = navHostController,
//        startDestination = Screen.Settings.route
//    ) {
//        composable(route = Screen.Settings.route) {
//            val viewModel: SettingsViewModel = hiltViewModel()
//            SettingsScreen(
//                onAppearanceSelected = {
//                    onAppearanceSelected(it)
//                },
//                onLanguageSelected = {
//                    onLanguageSelected(it)
//                },
//                viewModel = viewModel
//            )
//        }
//    }
//}