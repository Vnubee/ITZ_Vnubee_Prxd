package com.example.vnubee_prxd.datatypes

import kotlinx.serialization.Serializable

@Serializable
data class SettingsOptions(
    val showLikes: Boolean = false
)
