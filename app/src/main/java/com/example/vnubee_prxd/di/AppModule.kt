package com.example.vnubee_prxd.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.vnubee_prxd.datatypes.SettingsOptions
import com.example.vnubee_prxd.utils.SettingsSerializer
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebase() = Firebase.firestore
        .collection("Playlists")

    private val Context.dataStore: DataStore<SettingsOptions> by dataStore(
        fileName = "settings.json",
        serializer = SettingsSerializer
    )

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext app: Context
    ) : DataStore<SettingsOptions> = app.dataStore

    @Singleton
    @Provides
    fun provideDispatcherIo(): CoroutineDispatcher = Dispatchers.IO
}