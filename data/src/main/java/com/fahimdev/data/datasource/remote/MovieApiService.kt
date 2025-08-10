package com.fahimdev.data.datasource.remote

import com.fahimdev.data.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("list_movies.json")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<MovieListResponse>


}