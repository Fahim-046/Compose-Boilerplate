package com.fahimdev.domain.entities

data class PaginatedResult<T>(
    val data: List<T>,
    val currentPage: Int,
    val totalPages: Int,
    val hasNextPage: Boolean
)