package com.example.islami_project.api.model

data class RadioStation(
    val name: String,
    val audioResId: Int,
    var isPlaying: Boolean = false,
    var isLoading: Boolean = false,
    var isMuted: Boolean = false
)

