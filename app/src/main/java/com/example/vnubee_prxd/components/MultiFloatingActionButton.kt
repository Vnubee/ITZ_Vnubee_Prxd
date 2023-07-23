package com.example.vnubee_prxd.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vnubee_prxd.R
import com.example.vnubee_prxd.datatypes.MultiFabItem
import com.example.vnubee_prxd.datatypes.FloatingButtonState

@Composable
fun MultiFloatingActionButton(
    selectedItem: MultiFabItem,
    items: List<MultiFabItem>,
    toState: FloatingButtonState,
    showLabels: Boolean = true,
    stateChanged: () -> Unit,
    onFabItemClicked: (item: MultiFabItem) -> Unit
) {
    val transition: Transition<FloatingButtonState> = updateTransition(targetState = toState, label = "transition")

    val scale: Float by transition.animateFloat(label = "transition") { state ->
        if (state == FloatingButtonState.Expanded) 40f else 0f
    }

    val alpha: Float by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 50)
        }, label = "transition"
    ) { state ->
        if (state == FloatingButtonState.Expanded) 1f else 0f
    }

    val icon = ImageBitmap.imageResource(selectedItem.icon)

    Column(horizontalAlignment = Alignment.End) {
        if (scale != 0f){
            items.forEach { item ->
                if (item != selectedItem){
                    MiniFabItem(item, alpha, scale, showLabels, onFabItemClicked){ stateChanged() }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
        FloatingActionButton(
            onClick = { stateChanged() },
            shape = CircleShape,
            modifier = Modifier.border(
                BorderStroke(
                    4.dp,
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFF0000ff),
                            Color(0xFF00ff00)
                        )
                    )
                ),
                shape = CircleShape
            )
        ) {
            Canvas(
                modifier = Modifier
                    .size(56.dp)
            ) {
                drawImage(
                    icon,
                    topLeft = Offset(
                        (this.center.x) - (icon.width / 2),
                        (this.center.y) - (icon.width / 2)
                    ),
                    alpha = 1f
                )
            }
        }
    }
}

@Preview
@Composable
private fun Test(){
    MultiFloatingActionButton(
        selectedItem = MultiFabItem(R.drawable.a,""),
        items = listOf(),
        toState = FloatingButtonState.Collapsed,
        stateChanged = { /*TODO*/ },
        onFabItemClicked = {}
    )
}

@Composable
private fun MiniFabItem(
    item: MultiFabItem,
    alpha: Float,
    scale: Float,
    showLabel: Boolean,
    onFabItemClicked: (item: MultiFabItem) -> Unit,
    stateChanged: () -> Unit
) {
    val fabColor = MaterialTheme.colorScheme.secondary
    val shadowColor = Color.Black
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 12.dp)
    ) {
        if (showLabel) {
            Text(
                item.label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .alpha(alpha)
                    .padding(start = 6.dp, end = 6.dp, top = 4.dp, bottom = 4.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        val icon = ImageBitmap.imageResource(item.icon)
        Canvas(
            modifier = Modifier
                .size(32.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        stateChanged()
                        onFabItemClicked(item)
                    },
                )
        ) {
            drawCircle(
                shadowColor,
                center = Offset(this.center.x + 2f, this.center.y + 7f),
                radius = scale
            )
            drawCircle(
                color = fabColor,
                radius = scale
            )
            drawImage(
                icon,
                topLeft = Offset(
                    (this.center.x) - (icon.width / 2),
                    (this.center.y) - (icon.width / 2)
                ),
                alpha = alpha
            )
        }
    }
}
