package com.fahimdev.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieApiResponse(
    @SerialName("@meta")
    val meta: Meta,
    @SerializedName("`data`")
    val data: Data,
    val status: String,
    val status_message: String
)