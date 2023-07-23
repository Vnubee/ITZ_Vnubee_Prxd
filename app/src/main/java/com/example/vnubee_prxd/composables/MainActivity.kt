package com.example.vnubee_prxd.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.vnubee_prxd.components.MultiFloatingActionButton
import com.example.vnubee_prxd.components.Navigation
import com.example.vnubee_prxd.datatypes.FabIcon
import com.example.vnubee_prxd.datatypes.FloatingButtonState
import com.example.vnubee_prxd.datatypes.MultiFabItem
import com.example.vnubee_prxd.datatypes.SettingsOptions
import com.example.vnubee_prxd.ui.theme.VnubeePrxdTheme
import com.example.vnubee_prxd.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

//TODO: make tutorial that can be skipped

@AndroidEntryPoint
class MainActivity : ComponentActivity(){

    private val viewModel: MainViewModel by viewModels()
    private val fabIcon = FabIcon()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var toState by remember{
                mutableStateOf(FloatingButtonState.Collapsed)
            }
            val currPlaylist = viewModel.currPlaylist.value
            val vidList = viewModel.vidMap[currPlaylist]
            val navController = rememberNavController()
            val settings = viewModel.settings.collectAsState(initial = SettingsOptions())

            VnubeePrxdTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        topBar = {

                        },
                        floatingActionButton = {
                            MultiFloatingActionButton(
                                selectedItem = MultiFabItem(
                                    icon = fabIcon.getIcon(currPlaylist),
                                    label = currPlaylist,
                                ),
                                items = viewModel.playlistIcons.value,
                                toState = toState,
                                stateChanged = {
                                    toState = if (toState == FloatingButtonState.Expanded) {
                                        FloatingButtonState.Collapsed
                                    } else FloatingButtonState.Expanded
                                },
                                onFabItemClicked = {
                                    viewModel.changePlaylist(it)
                                },
                            )
                        },
                    ) { innerPadding ->

                        Navigation(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding),
                            vidList,
                            toState = toState,
                            settingsOptions = settings.value,
                            toggleState = {
                                toState = if (toState == FloatingButtonState.Expanded) {
                                    FloatingButtonState.Collapsed
                                } else FloatingButtonState.Expanded
                            },
                            onSettingsChanged = viewModel::changeSettings
                        )
                    }
                }
            }
        }
    }
}
