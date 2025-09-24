package com.fahimdev.shared.data.datasource.remote.apiClient

import com.fahimdev.shared.getHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentLength
import io.ktor.http.contentType

class ApiClient(
    private val baseUrl: String = "https://yts.mx/api/v2/",
    private val apiKey: String? = null,
    private val authType: AuthType = AuthType.BEARER
) {
    val client = getHttpClient()

    suspend inline fun <reified T> get(
        endpoint: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return client.get("$baseUrl$endpoint", block).body()
    }

    suspend inline fun <reified T> post(
        endpoint: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return client.post("$baseUrl$endpoint", block).body()
    }

    fun close() {
        client.close()
    }

    enum class AuthType {
        BEARER,
        API_KEY,
        QUERY_PARAM
    }
}