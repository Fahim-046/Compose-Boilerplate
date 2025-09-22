package com.fahimdev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val limit: Int,
    val movie_count: Int,
    val movies: List<MovieResponse>,
    val page_number: Int
)