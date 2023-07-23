package com.example.vnubee_prxd.retrofit

import com.example.vnubee_prxd.datatypes.PlaylistData
import com.example.vnubee_prxd.datatypes.VideoData
import com.example.vnubee_prxd.utils.YoutubeMapper
import javax.inject.Inject

class DTO @Inject
constructor(): YoutubeMapper<PlaylistEntity, VideoEntity> {

    override fun mapFromPlaylist(playlist: PlaylistEntity): PlaylistData {
        return PlaylistData(playlist.playlistItems?.map { item -> item.contentDetails?.videoId }, playlist.nextPageToken)
    }

    override fun mapFromVideo(video: VideoEntity): List<VideoData> {
        return video.items.map { videoItem ->
            VideoData(
                isYoutube = true,
                videoID = videoItem.id,
                videoName = videoItem.snippet?.title,
                thumbnail = videoItem.snippet?.thumbnails?.high?.url,
                views = videoItem.statistics?.viewCount,
                likes = videoItem.statistics?.likeCount,
                comments = videoItem.statistics?.commentCount,
            )
        }


    }
}