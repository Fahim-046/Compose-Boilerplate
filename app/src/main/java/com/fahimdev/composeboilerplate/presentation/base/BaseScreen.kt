package com.fahimdev.composeboilerplate.presentation.base

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation3.runtime.NavBackStack
import com.fahimdev.composeboilerplate.ui.components.dialog.LoadingDialog
import com.fahimdev.composeboilerplate.ui.components.navigation.BottomNavigationBar

@Composable
fun BaseScreen(
    title: String,
    viewModel: BaseViewModel = BaseViewModel(),
    navHostController: NavHostController = rememberNavController(),
    navBackStack: NavBackStack? = null,
    showTopBar: Boolean = true,
    showBackArrow: Boolean = true,
    topBar: @Composable () -> Unit = {

    },
    showBottomBar: Boolean = false,
    showBottomNavigation: Boolean = false,
    bottomBar: @Composable () -> Unit = {},
    screenContent: @Composable () -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = context) {
        viewModel.baseUiEvent.collect { event ->
            when (event) {
                is BaseUiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }

                is BaseUiEvent.Navigate -> {
                    navHostController.navigate(event.route)
                }

                BaseUiEvent.PopBackStack -> {
                    navHostController.popBackStack()
                }

                is BaseUiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Scaffold(
        topBar = { if (showTopBar) topBar() },
        bottomBar = {
            when {
                showBottomNavigation && navBackStack != null -> {
                    BottomNavigationBar(backStack = navBackStack)
                }
                showBottomBar -> bottomBar()
            }
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        modifier = Modifier.statusBarsPadding()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LoadingDialog(
                isVisible = state.isLoading,
                onDismiss = {
                    viewModel.onBaseEvent(BaseEvent.OnDismissLoadingDialog)
                }
            )
            screenContent()
        }

    }
}