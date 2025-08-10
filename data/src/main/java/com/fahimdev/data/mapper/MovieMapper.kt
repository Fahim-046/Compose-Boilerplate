package com.fahimdev.data.mapper

import com.fahimdev.data.model.MovieResponse
import com.fahimdev.domain.entities.Movie

class MovieMapper {
    companion object {
        fun mapResponseToDomain(response: MovieResponse): Movie {
            return Movie(
                backgroundImage = response.backgroundImage,
                backgroundImageOriginal = response.backgroundImageOriginal,
                descriptionFull = response.descriptionFull,
                genres = response.genres,
                id = response.id,
                imdbCode = response.imdbCode,
                language = response.language,
                largeCoverImage = response.largeCoverImage,
                mediumCoverImage = response.mediumCoverImage,
                mpaRating = response.mpaRating,
                rating = response.rating,
                runtime = response.runtime,
                slug = response.slug,
                smallCoverImage = response.smallCoverImage,
                state = response.state,
                summary = response.summary,
                synopsis = response.synopsis,
                title = response.title,
                titleEnglish = response.titleEnglish,
                titleLong = response.titleLong,
                url = response.url,
                year = response.year,
                ytTrailerCode = response.ytTrailerCode
            )
        }
    }
}