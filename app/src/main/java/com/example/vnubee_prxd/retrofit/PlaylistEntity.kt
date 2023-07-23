package com.example.vnubee_prxd.retrofit

import com.google.gson.annotations.SerializedName

data class PlaylistEntity(

    @SerializedName("items")
    val playlistItems: List<PlaylistItems>?,

    @SerializedName("nextPageToken")
    val nextPageToken: String?,
)

data class PlaylistItems(

    @SerializedName("contentDetails")
    val contentDetails: ContentDetails?,
)

data class ContentDetails(

    @SerializedName("videoId")
    val videoId: String?
)
