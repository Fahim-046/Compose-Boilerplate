package com.fahimdev.shared.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieApiResponse(
    @SerialName("@meta")
    val meta: Meta,
    val data: Data,
    val status: String,
    val status_message: String
)