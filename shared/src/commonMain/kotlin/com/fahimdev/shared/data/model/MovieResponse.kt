package com.fahimdev.shared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val background_image: String,
    val background_image_original: String,
    val date_uploaded: String,
    val date_uploaded_unix: Int,
    val description_full: String,
    val genres: List<String>,
    val id: Int,
    val imdb_code: String,
    val language: String,
    val large_cover_image: String,
    val medium_cover_image: String,
    val mpa_rating: String,
    val rating: Double,
    val runtime: Int,
    val slug: String,
    val small_cover_image: String,
    val state: String,
    val summary: String,
    val synopsis: String,
    val title: String,
    val title_english: String,
    val title_long: String,
    val torrents: List<Torrent>,
    val url: String,
    val year: Int,
    val yt_trailer_code: String
)