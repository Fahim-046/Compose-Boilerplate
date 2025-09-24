package com.fahimdev.shared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Torrent(
    val audio_channels: String,
    val bit_depth: String,
    val date_uploaded: String,
    val date_uploaded_unix: Int,
    val hash: String,
    val is_repack: String,
    val peers: Int,
    val quality: String,
    val seeds: Int,
    val size: String,
    val size_bytes: Long,
    val type: String,
    val url: String,
    val video_codec: String
)