package com.example.vnubee_prxd.repository

import com.example.vnubee_prxd.datatypes.Playlist
import com.example.vnubee_prxd.datatypes.PlaylistData
import com.example.vnubee_prxd.datatypes.VideoData
import com.example.vnubee_prxd.retrofit.ApiService
import com.example.vnubee_prxd.retrofit.DTO
import com.example.vnubee_prxd.utils.Constants.API_KEY
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val retrofitService: ApiService,
    private val mapper: DTO
) {
    suspend fun getVideo(playlists: List<Playlist>?): MutableMap<String, List<VideoData>> {
        var playlistData: PlaylistData?
        val categoryData: MutableMap<String, List<VideoData>> = mutableMapOf()
        playlists?.forEach { playlist ->
            playlistData = playlist.playlistId?.let { Id -> retrofitService.getPlaylist(part = "contentDetails", playlistId = Id, maxResults = 20, key = API_KEY).body()?.let { entity -> mapper.mapFromPlaylist(entity) } } ?: PlaylistData(
                listOf(), null)
            while (playlistData?.nextPageToken != null){
                val nextPage = playlist.playlistId?.let { Id -> retrofitService.getPlaylist(part = "contentDetails", playlistId = Id, maxResults = 20, pageToken = playlistData?.nextPageToken,key = API_KEY).body()?.let { entity -> mapper.mapFromPlaylist(entity) } }
                playlistData?.nextPageToken = nextPage?.nextPageToken
                nextPage?.videoID?.forEach { videoId->
                    playlistData?.videoID = playlistData?.videoID?.plus(videoId)
                }
            }

            categoryData[playlist.name] = mapper.mapFromVideo(retrofitService.getVideo("snippet, statistics", playlistData?.videoID?.plus(playlist.videoId)?.joinToString(separator = ","), API_KEY).body()!!)
            playlist.driveId?.let { driveIds ->
                driveIds.forEach { driveId ->
                    categoryData[playlist.name]?.let { vidList ->
                        categoryData[playlist.name] = vidList.plus(
                            VideoData(
                            videoName = driveId["videoName"] as String,
                            videoID = driveId["videoId"] as String,
                            thumbnail = "https://drive.google.com/uc?id=${driveId["thumbnailId"]}",
                            isYoutube = false
                        )
                        )
                    }
                }
            }
        }
        return categoryData
    }
}