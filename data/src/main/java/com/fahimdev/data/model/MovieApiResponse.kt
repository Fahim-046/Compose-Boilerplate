package com.fahimdev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieApiResponse(
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int
)