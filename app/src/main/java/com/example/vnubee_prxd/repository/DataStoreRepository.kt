package com.example.vnubee_prxd.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.vnubee_prxd.datatypes.SettingsOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<SettingsOptions>,
    private val ioDispatcher: CoroutineDispatcher
) {
    val settingsFlow: Flow<SettingsOptions> = dataStore.data
        .catch { exception->
            if(exception is IOException){
                Log.d("Error: IO", exception.message.toString())
                emit(SettingsOptions())
            }else{
                throw exception
            }
        }

    suspend fun storeUser(settingsOptions: SettingsOptions) = withContext(ioDispatcher) {
        dataStore.updateData {
            settingsOptions
        }
    }
}