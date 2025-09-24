package com.fahimdev.shared.network

import io.ktor.client.*
import io.ktor.client.engine.js.*

actual fun createHttpClient(): HttpClient = HttpClient(Js)