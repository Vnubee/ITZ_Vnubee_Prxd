package com.example.vnubee_prxd.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vnubee_prxd.datatypes.FabIcon
import com.example.vnubee_prxd.datatypes.MultiFabItem
import com.example.vnubee_prxd.datatypes.Playlist
import com.example.vnubee_prxd.datatypes.SettingsOptions
import com.example.vnubee_prxd.datatypes.VideoData
import com.example.vnubee_prxd.R
import com.example.vnubee_prxd.repository.DataStoreRepository
import com.example.vnubee_prxd.repository.MainRepository
import com.google.firebase.firestore.CollectionReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val playlistReference: CollectionReference
): ViewModel() {


    val settings = dataStoreRepository.settingsFlow
    val vidMap = mutableStateMapOf<String, List<VideoData>>()
    val currPlaylist: MutableState<String> = mutableStateOf("*")
    val playlistIcons: MutableState<List<MultiFabItem>> = mutableStateOf(listOf(MultiFabItem(R.drawable.unknown, "unknown")))

    init {
        getData()
    }

    private fun getData(){
        viewModelScope.launch (Dispatchers.IO) {
            try{
                val playlists = playlistReference.get().await().toObjects(Playlist::class.java)
                currPlaylist.value = playlists[0].name
                vidMap.putAll(mainRepository.getVideo(playlists))
                playlistIcons.value = vidMap.map { item -> MultiFabItem(icon = FabIcon().getIcon(item.key), label = item.key) }
            }
            catch(err: Exception){
                Log.e("NETWORK ERROR", err.toString())
            }
        }
    }

    fun changePlaylist(item: MultiFabItem) {
        viewModelScope.launch {
            currPlaylist.value = item.label
        }
    }

    fun changeSettings(settingsOptions: SettingsOptions) {
        viewModelScope.launch {
            dataStoreRepository.storeUser(settingsOptions)
        }
    }
}
