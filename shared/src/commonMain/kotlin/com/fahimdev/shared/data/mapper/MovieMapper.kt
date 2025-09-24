package com.fahimdev.shared.data.mapper

import com.fahimdev.shared.data.model.MovieResponse
import com.fahimdev.shared.domain.entities.Movie

class MovieMapper {
    companion object {
        fun mapResponseToDomain(response: MovieResponse?): Movie? {
            if (response == null) return null
            return Movie(
                id = response.id,
                title = response.title,
                year = response.year,
                rating = response.rating,
                runtime = response.runtime,
                genres = response.genres,
                summary = response.summary,
                coverImage = response.background_image_original,
                imdbCode = response.imdb_code
            )
        }
    }
}