package com.example.vnubee_prxd.utils

import com.example.vnubee_prxd.datatypes.PlaylistData
import com.example.vnubee_prxd.datatypes.VideoData

interface YoutubeMapper<Playlist,Video> {
    fun mapFromPlaylist(playlist: Playlist): PlaylistData

    fun mapFromVideo(video: Video): List<VideoData>
}