package com.fahimdev.data.datasource.remote.apiClient

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentLength
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiClient(
    private val baseUrl: String = "https://yts.mx/api/v2/",
    private val apiKey: String? = null,
    private val authType: AuthType = AuthType.BEARER
) {
    val client = HttpClient(OkHttp) {
        install(Logging) {
            level = LogLevel.BODY
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        install(DefaultRequest) {
            url(baseUrl)
            apiKey?.let { key ->
                when (authType) {
                    AuthType.BEARER -> header("Authorization", "Bearer $key")
                    AuthType.API_KEY -> header("Authorization", key)
                    AuthType.QUERY_PARAM -> {
                        url {
                            parameters.append("api_key", key)
                        }
                    }
                }
            }
        }

        install(ResponseObserver) {
            onResponse { response ->
                logResponse(response)
            }
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 100_000
            connectTimeoutMillis = 100_000
            socketTimeoutMillis = 100_000
        }
    }


    private suspend fun logResponse(response: HttpResponse) {
        val statusCode = response.status
        val headers = response.headers

        println("📥 ========== RESPONSE ==========")
        println("📥 Status: ${statusCode.value} ${statusCode.description}")

        // Response headers
        if (headers.names().isNotEmpty()) {
            println("📥 Response Headers:")
            headers.forEach { name, values ->
                values.forEach { value ->
                    println("📥   $name: $value")
                }
            }
        }

        val contentType = response.contentType()
        val contentLength = response.contentLength()

        println("📥 Content-Type: $contentType")
        println("📥 Content-Length: ${contentLength ?: "Unknown"}")

        try {
            if (contentType?.match(ContentType.Application.Json) == true) {
                val responseBody = response.bodyAsText()
                if (responseBody.length < 1000) {
                    println("📥 Response Body: $responseBody")
                } else {
                    println("📥 Response Body: [Large JSON response - ${responseBody.length} characters]")
                }
            }
        } catch (e: Exception) {
            println("📥 Could not read response body: ${e.message}")
        }

        when {
            statusCode.isSuccess() -> println("✅ Request Successful")
            statusCode.value in 400..499 -> println("❌ Client Error")
            statusCode.value in 500..599 -> println("🔥 Server Error")
            else -> println("ℹ️ Other Status")
        }

        println("📥 ===============================")
    }

    suspend inline fun <reified T> get(
        endpoint: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return client.get(endpoint, block).body()
    }

    suspend inline fun <reified T> post(
        endpoint: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return client.post(endpoint, block).body()
    }

    fun close() {
        client.close()
    }

    private fun HttpStatusCode.isSuccess(): Boolean = value in 200..299

    enum class AuthType {
        BEARER,
        API_KEY,
        QUERY_PARAM
    }
}