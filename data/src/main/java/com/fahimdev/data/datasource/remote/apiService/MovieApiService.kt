package com.fahimdev.data.datasource.remote.apiService

import com.fahimdev.data.datasource.remote.apiClient.ApiClient
import com.fahimdev.data.model.MovieApiResponse
import com.fahimdev.data.model.MovieResponse
import javax.inject.Inject

class MovieApiService @Inject constructor(
    private val apiClient: ApiClient
) {
    suspend fun getTrendingMovies(page: Int = 1): MovieApiResponse {
        return apiClient.get("movie/now_playing"){
            url {
                parameters.append("page", "$page")
            }
        }
    }

    suspend fun getUpcomingMovies(page: Int = 1): MovieApiResponse {
        return apiClient.get("movie/upcoming"){
            url {
                parameters.append("page", "$page")
            }
        }
    }

    suspend fun getPopularMovies(page: Int = 1): MovieApiResponse {
        return apiClient.get("movie/popular"){
            url {
                parameters.append("page", "$page")
            }
        }
    }

    suspend fun getMovieDetails(id: Int): MovieResponse {
        return apiClient.get("movie/$id")
    }
}