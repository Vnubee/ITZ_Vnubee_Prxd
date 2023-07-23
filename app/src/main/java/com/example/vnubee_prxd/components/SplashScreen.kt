package com.example.vnubee_prxd.components

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vnubee_prxd.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    var visible by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    LaunchedEffect(key1 = true){
        visible = true
        delay(1100L)
        navController.navigate(route="home"){
            launchSingleTop = true
            popUpTo(0)
        }
    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x00000000))
    ){
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AnimatedVisibility(
                visible = visible,
                enter = slideInVertically(
                    animationSpec = tween(2100,
                        easing = {
                            OvershootInterpolator(1f).getInterpolation(it)
                        }
                    )
                ){
                    with(density) { -200.dp.roundToPx() }
                }
            ) {
                Image(
                    painter = painterResource(id = (R.drawable.logo)),
                    contentDescription = "logo",
                )
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(
                    animationSpec = tween(500)
                )
            ) {
                Text(
                    text = "Vnubee Prxd",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Text(
            text = "Powered by",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(12.dp)
        )
    }
}
