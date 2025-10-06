package com.fahimdev.data.datasource.remote.apiClient

import com.fahimdev.data.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class ApiClient(
    private val baseUrl: String,
    private val apiKey: String?,
    private val authType: AuthType = AuthType.QUERY_PARAM
) {
    val client = HttpClient(OkHttp) {
        engine {
            preconfigured = createOkHttpClient()
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

        install(HttpTimeout) {
            requestTimeoutMillis = 100_000
            connectTimeoutMillis = 100_000
            socketTimeoutMillis = 100_000
        }
    }

    private fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

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
                .addInterceptor(loggingInterceptor)
                .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier { _, _ -> true } // Accept all hostnames
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .build()
        } catch (e: Exception) {
            println("⚠️ SSL configuration failed, using default client: ${e.message}")
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .build()
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