package com.example.vnubee_prxd.retrofit

import com.example.vnubee_prxd.utils.Constants.PLAYLIST_GET
import com.example.vnubee_prxd.utils.Constants.VIDEO_GET
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(PLAYLIST_GET)
    suspend fun getPlaylist(
        @Query(value = "part") part: String,
        @Query(value = "playlistId") playlistId: String,
        @Query(value = "maxResults") maxResults: Int,
        @Query(value = "pageToken") pageToken: String? = "",
        @Query(value = "key") key: String,
    ): Response<PlaylistEntity>

    @GET(VIDEO_GET)
    suspend fun getVideo(
        @Query(value = "part") part: String?,
        @Query(value = "id") id: String?,
        @Query(value = "key") key: String?,
    ): Response<VideoEntity>
}