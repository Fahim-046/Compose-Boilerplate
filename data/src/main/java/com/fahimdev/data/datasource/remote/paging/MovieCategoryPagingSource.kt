package com.fahimdev.data.datasource.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fahimdev.domain.entities.Movie
import com.fahimdev.domain.repository.MovieRepository

class MovieCategoryPagingSource(
    private val category: String,
    private val movieRepository: MovieRepository
): PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            val paginatedResult = movieRepository.getMoviesByCategoryPaginated(category, page)

            LoadResult.Page(
                data = paginatedResult.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (!paginatedResult.hasNextPage) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}