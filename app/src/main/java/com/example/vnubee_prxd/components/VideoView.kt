package com.example.vnubee_prxd.components

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.vnubee_prxd.datatypes.VideoData

@Composable
fun VideoView(videoData: VideoData, showLikes: Boolean){

    val context  = LocalContext.current
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(.96f),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ){
        val painter = rememberImagePainter(
            data = videoData.thumbnail
        )
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(
                    8.dp
                )
                .fillMaxWidth()) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .size(
                        width = 340.dp,
                        height = 260.dp
                    )
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(4.dp, Brush.horizontalGradient(listOf(Color(0xFF0000ff), Color(0xFF00ff00))))
            ) {
                val onClick = if (videoData.isYoutube) ({
                    val intent = Intent(context, Video::class.java)
                    intent.putExtra("videoId", videoData.videoID)
                    context.startActivity(intent)
                })
                else ({
                    uriHandler.openUri("https://drive.google.com/file/d/${videoData.videoID}/view")
                })

                Image(
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = "loading",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            enabled = true,
                            onClickLabel = "Play Video",
                            onClick = onClick
                        )
                )
            }
            videoData.videoName?.let {
                Text(
                    text= it,
                    fontSize = 30.sp,
                    modifier=Modifier
                        .fillMaxWidth()
                )
            }
            if (videoData.isYoutube && showLikes){
                Text(text = "${videoData.views} views  and ${videoData.likes} likes and ${videoData.comments} comments")
            }
        }
    }
}
