package com.fahimdev.composeboilerplate.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.unit.dp
import com.fahimdev.shared.ui.App

fun main() = application {
    val windowState = rememberWindowState(
        width = 1200.dp,
        height = 800.dp
    )

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "Compose Boilerplate - Desktop"
    ) {
        App()
    }
}