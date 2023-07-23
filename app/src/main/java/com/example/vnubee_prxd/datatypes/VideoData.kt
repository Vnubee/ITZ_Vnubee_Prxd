package com.example.vnubee_prxd.datatypes

import kotlinx.serialization.Serializable

@Serializable
data class VideoData(
    val videoID: String?,
    val videoName: String?,
    val isYoutube: Boolean,
    val thumbnail: String? = "",
    val views: Long? = 0,
    val likes: Long? = 0,
    val comments: Long? = 0
)
