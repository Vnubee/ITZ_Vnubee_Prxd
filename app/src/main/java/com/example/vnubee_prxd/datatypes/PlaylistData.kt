package com.example.vnubee_prxd.datatypes

import kotlinx.serialization.Serializable

@Serializable
data class PlaylistData(
    var videoID: List<String?>?,
    var nextPageToken: String?
)