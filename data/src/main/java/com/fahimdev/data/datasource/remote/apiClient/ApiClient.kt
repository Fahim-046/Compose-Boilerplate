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
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentLength
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class ApiClient(
    private val baseUrl: String = "https://api.themoviedb.org/3/",
    private val apiKey: String? = "db5d93ff881544f1df428a417958ce1c",
    private val authType: AuthType = AuthType.QUERY_PARAM
) {
    val client = HttpClient(OkHttp) {
        engine {
            preconfigured = createOkHttpClient()
        }

        install(Logging) {
            level = LogLevel.BODY
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    coerceInputValues = true
                    encodeDefaults = true
                }
            )
        }

        install(DefaultRequest) {
            url(baseUrl)
            contentType(ContentType.Application.Json)
            header("Accept", "application/json")
            header("User-Agent", "MovieApp/1.0")
            apiKey?.let { key ->
                when (authType) {
                    AuthType.BEARER -> header("Authorization", "Bearer $key")
                    AuthType.API_KEY -> header("Authorization", key)
                    AuthType.QUERY_PARAM -> {
                        url {
                            parameters.append("api_key", key)
                        }
                    }

                    AuthType.NONE -> {}
                }
            }
        }

        install(ResponseObserver) {
            onResponse { response ->
                logRequest(response.request)
                logResponse(response)
            }
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 100_000
            connectTimeoutMillis = 100_000
            socketTimeoutMillis = 100_000
        }
    }

    private fun createOkHttpClient(): OkHttpClient {
        return try {
            // Create a trust manager that accepts all certificates (for development)
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            OkHttpClient.Builder()
                .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier { _, _ -> true } // Accept all hostnames
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .build()
        } catch (e: Exception) {
            println("⚠️ SSL configuration failed, using default client: ${e.message}")
            OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .build()
        }
    }

    private fun logRequest(request: io.ktor.client.request.HttpRequest) {
        println("📤 ========== REQUEST ==========")
        println("📤 Method: ${request.method.value}")
        println("📤 URL: ${request.url}")

        // Request headers
        if (request.headers.names().isNotEmpty()) {
            println("📤 Request Headers:")
            request.headers.forEach { name, values ->
                values.forEach { value ->
                    // Mask sensitive headers
                    val displayValue = when {
                        name.equals("Authorization", ignoreCase = true) -> maskSensitiveValue(value)
                        name.equals("api_key", ignoreCase = true) -> maskSensitiveValue(value)
                        else -> value
                    }
                    println("📤   $name: $displayValue")
                }
            }
        }

        val contentType = request.contentType()
        val contentLength = request.headers["Content-Length"]

        println("📤 Content-Type: $contentType")
        println("📤 Content-Length: ${contentLength ?: "Unknown"}")

        // Log query parameters (mask sensitive ones)
        val parameters = request.url.parameters
        if (parameters.names().isNotEmpty()) {
            println("📤 Query Parameters:")
            parameters.forEach { name, values ->
                values.forEach { value ->
                    val displayValue = when {
                        name.equals("api_key", ignoreCase = true) -> maskSensitiveValue(value)
                        name.equals("access_token", ignoreCase = true) -> maskSensitiveValue(value)
                        else -> value
                    }
                    println("📤   $name: $displayValue")
                }
            }
        }

        println("📤 ==============================")
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
                println("📥 Response Body: $responseBody")
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

    private fun maskSensitiveValue(value: String): String {
        return when {
            value.length <= 8 -> "***"
            else -> "${value.take(4)}****${value.takeLast(4)}"
        }
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
        QUERY_PARAM,
        NONE
    }
}