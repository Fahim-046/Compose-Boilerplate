package com.fahimdev.shared.network

import io.ktor.client.*
import io.ktor.client.engine.android.*

actual fun createHttpClient(): HttpClient = HttpClient(Android)