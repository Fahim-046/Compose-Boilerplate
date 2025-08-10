package com.fahimdev.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("background_image_original")
    val backgroundImageOriginal: String,
    @SerializedName("description_full")
    val descriptionFull: String,
    val genres: List<String>,
    val id: Int,
    @SerializedName("imdb_code")
    val imdbCode: String,
    val language: String,
    @SerializedName("large_cover_image")
    val largeCoverImage: String,
    @SerializedName("medium_cover_image")
    val mediumCoverImage: String,
    @SerializedName("mpa_rating")
    val mpaRating: String,
    val rating: Double,
    val runtime: Int,
    val slug: String,
    @SerializedName("small_cover_image")
    val smallCoverImage: String,
    val state: String,
    val summary: String,
    val synopsis: String,
    val title: String,
    @SerializedName("title_english")
    val titleEnglish: String,
    @SerializedName("title_long")
    val titleLong: String,
    val url: String,
    val year: Int,
    @SerializedName("yt_trailer_code")
    val ytTrailerCode: String
)