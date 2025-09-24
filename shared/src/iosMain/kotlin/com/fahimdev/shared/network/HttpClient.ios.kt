package com.fahimdev.shared.network

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual fun createHttpClient(): HttpClient = HttpClient(Darwin)