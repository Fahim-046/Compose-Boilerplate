package com.fahimdev.shared.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val year: Int,
    val rating: Double,
    val runtime: Int,
    val genres: List<String>,
    val summary: String,
    val coverImage: String,
    val imdbCode: String
)