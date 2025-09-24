package com.fahimdev.shared.data.datasource.remote.apiService

import com.fahimdev.shared.data.datasource.remote.apiClient.ApiClient
import com.fahimdev.shared.data.model.MovieApiResponse
import com.fahimdev.shared.data.model.MovieResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieApiService(
    private val apiClient: ApiClient
) {
    suspend fun getMovies(): List<MovieResponse> {
        val response = apiClient.client.get("list_movies.json").body<MovieApiResponse>()
        return response.data.movies
    }
}