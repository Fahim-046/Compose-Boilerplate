package com.fahimdev.data.datasource.remote

import com.fahimdev.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {
    @GET("/characters")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<List<CharacterResponse>>

    @GET("/characters/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterResponse>
}