package com.fahimdev.core.network

sealed class ApiException(val message: String? = null, val code: Int? = null) {
    data object NetworkError: ApiException()
    data object UnknownError: ApiException()
}