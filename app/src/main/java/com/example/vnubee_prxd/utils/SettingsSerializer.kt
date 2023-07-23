package com.example.vnubee_prxd.utils

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.vnubee_prxd.datatypes.SettingsOptions
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object SettingsSerializer : Serializer<SettingsOptions> {
    override val defaultValue: SettingsOptions
        get() = SettingsOptions()

    override suspend fun readFrom(input: InputStream): SettingsOptions {
        try {
            return Json.decodeFromString(
                deserializer = SettingsOptions.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(
        t: SettingsOptions,
        output: OutputStream
    ) = output.write(
        Json.encodeToString(
            serializer = SettingsOptions.serializer(),
            value = t
        ).encodeToByteArray()
    )
}