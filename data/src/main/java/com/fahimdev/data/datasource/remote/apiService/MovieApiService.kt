package com.fahimdev.data.datasource.remote.apiService

import com.fahimdev.data.datasource.remote.apiClient.ApiClient
import com.fahimdev.data.model.MovieApiResponse
import com.fahimdev.data.model.MovieResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class MovieApiService @Inject constructor(
    private val apiClient: ApiClient
) {
    suspend fun getMovies(): List<MovieResponse> {
        val response = apiClient.client.get("list_movies.json").body<MovieApiResponse>()
        return response.data.movies
    }
}