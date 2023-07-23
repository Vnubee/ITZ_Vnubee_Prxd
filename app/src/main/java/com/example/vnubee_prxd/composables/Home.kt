package com.example.vnubee_prxd.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.example.vnubee_prxd.components.VideoView
import com.example.vnubee_prxd.datatypes.FloatingButtonState
import com.example.vnubee_prxd.datatypes.VideoData

@Composable
fun Home(
    vidList: List<VideoData>?,
    modifier: Modifier,
    toState: FloatingButtonState,
    showLikes: Boolean,
    toggleState: () -> Unit
) {
    vidList?.let { items ->
        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(
                items = items
            ) { _, item ->
                VideoView(
                    videoData = item,
                    showLikes = showLikes
                )
            }
        }

        if (toState == FloatingButtonState.Expanded) {
            Box(
                modifier = Modifier
                    .alpha(0.6f)
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        toggleState()
                    }
            )
        }
    }
}
