package com.example.muvitracker.inkotlin.domain.model


data class PrefsItem(
    val liked: Boolean,
    val watched: Boolean,
    val detail : DetailsItem // o dto
    )