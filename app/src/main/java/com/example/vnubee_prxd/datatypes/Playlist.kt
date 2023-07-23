package com.example.vnubee_prxd.datatypes

import kotlinx.serialization.Serializable

@Serializable
data class Playlist(
    val name: String = "",
    val playlistId: String? = null,
    val videoId: List<String> = listOf(),
    val driveId: List<Map<String, String>>? = null
)