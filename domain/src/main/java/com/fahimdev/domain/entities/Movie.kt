package com.fahimdev.domain.entities

data class Movie(
    val backgroundImage: String,
    val backgroundImageOriginal: String,
    val descriptionFull: String,
    val genres: List<String>,
    val id: Int,
    val imdbCode: String,
    val language: String,
    val largeCoverImage: String,
    val mediumCoverImage: String,
    val mpaRating: String,
    val rating: Double,
    val runtime: Int,
    val slug: String,
    val smallCoverImage: String,
    val state: String,
    val summary: String,
    val synopsis: String,
    val title: String,
    val titleEnglish: String,
    val titleLong: String,
    val url: String,
    val year: Int,
    val ytTrailerCode: String
)