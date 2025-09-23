package com.fahimdev.data.datasource.remote.apiService

import com.fahimdev.data.datasource.remote.apiClient.ApiClient
import com.fahimdev.data.model.MovieApiResponse
import javax.inject.Inject

class MovieApiService @Inject constructor(
    private val apiClient: ApiClient
) {
    suspend fun getMovies(page: Int = 1): MovieApiResponse {
        return apiClient.get("movie/popular"){
            url {
                parameters.append("page", "$page")
            }
        }
    }
}