package com.example.vnubee_prxd.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.vnubee_prxd.composables.Home
import com.example.vnubee_prxd.composables.Settings
import com.example.vnubee_prxd.datatypes.FloatingButtonState
import com.example.vnubee_prxd.datatypes.SettingsOptions
import com.example.vnubee_prxd.datatypes.VideoData

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier,
    vidList: List<VideoData>?,
    toState: FloatingButtonState,
    settingsOptions: SettingsOptions,
    toggleState: () -> Unit,
    onSettingsChanged: (SettingsOptions) -> Unit
) {
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("home") {
            Home(
                vidList = vidList,
                modifier = modifier,
                toState = toState,
                showLikes = settingsOptions.showLikes,
                toggleState = toggleState
            )
        }
        
        composable("settings") {
            Settings(
                options = settingsOptions,
                onSettingsChanged = onSettingsChanged
            )
        }
        
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
    }
}