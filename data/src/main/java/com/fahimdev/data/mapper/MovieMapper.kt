package com.fahimdev.data.mapper

import com.fahimdev.data.model.MovieResponse
import com.fahimdev.domain.entities.Movie

class MovieMapper {
    companion object{
        fun mapResponseToDomain(response: MovieResponse?): Movie? {
            if(response == null) return null

            val year = try {
                response.release_date.split("-")[0].toIntOrNull() ?: 2023
            } catch (e: Exception) {
                2023
            }

            val genres = mapGenreIdsToNames(response.genre_ids ?: emptyList())

            val coverImage = response.poster_path?.let {
                "https://image.tmdb.org/t/p/w500$it"
            } ?: ""

            return Movie(
                id = response.id,
                title = response.title,
                year = year,
                rating = response.vote_average,
                runtime = 120,
                genres = genres,
                summary = response.overview,
                coverImage = coverImage,
                imdbCode = "tt${response.id}"
            )
        }

        private fun mapGenreIdsToNames(genreIds: List<Int>): List<String> {
            val genreMap = mapOf(
                28 to "Action",
                12 to "Adventure",
                16 to "Animation",
                35 to "Comedy",
                80 to "Crime",
                99 to "Documentary",
                18 to "Drama",
                10751 to "Family",
                14 to "Fantasy",
                36 to "History",
                27 to "Horror",
                10402 to "Music",
                9648 to "Mystery",
                10749 to "Romance",
                878 to "Science Fiction",
                10770 to "TV Movie",
                53 to "Thriller",
                10752 to "War",
                37 to "Western"
            )

            return genreIds.mapNotNull { genreMap[it] }
        }
    }
}