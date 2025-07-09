package com.example.islami_project.api.model

data class MutableRadiosItem(
    val id: Int,
    val name: String,
    val url: String,
    var isMuted: Boolean = false,
    var isPlaying: Boolean = false,
    var isPaused: Boolean = false,
    var volumeLevel: Int = 100
)
