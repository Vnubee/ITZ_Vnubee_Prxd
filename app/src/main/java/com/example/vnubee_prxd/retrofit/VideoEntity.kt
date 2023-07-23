package com.example.vnubee_prxd.retrofit

import com.google.gson.annotations.SerializedName

data class VideoEntity(
    val items: List<VideoItem>
)

data class VideoItem(

    @SerializedName("id")
    val id: String?,

    @SerializedName("snippet")
    val snippet: Snippet?,

    @SerializedName("statistics")
    val statistics: Statistics?,
)

data class Snippet(

    @SerializedName("title")
    val title: String?,

    @SerializedName("thumbnails")
    val thumbnails: Thumbnails?
)

data class Statistics(

    @SerializedName("viewCount")
    val viewCount: Long?,

    @SerializedName("likeCount")
    val likeCount: Long?,

    @SerializedName("commentCount")
    val commentCount: Long?
)

data class Thumbnails(

    @SerializedName("high")
    val high: High?
)
data class High(
    val url: String?
)