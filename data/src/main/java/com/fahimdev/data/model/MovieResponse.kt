package com.fahimdev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val id: Int,
    val title: String,
    val original_title: String,
    val original_language: String,
    val overview: String,
    val release_date: String,
    val adult: Boolean,
    val video: Boolean,
    val popularity: Double,
    val vote_average: Double,
    val vote_count: Int,
    val poster_path: String?,
    val backdrop_path: String?,
    val genre_ids: List<Int>? = null
)