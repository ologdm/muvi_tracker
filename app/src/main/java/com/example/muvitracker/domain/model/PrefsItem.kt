package com.example.muvitracker.domain.model


data class PrefsItem(
    val liked: Boolean,
    val watched: Boolean,
    val detail : DetailMovie // o dto
    )