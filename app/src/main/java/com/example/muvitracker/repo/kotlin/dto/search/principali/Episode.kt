package com.example.muvitracker.repo.kotlin.dto.search.principali

import com.example.muvitracker.repo.kotlin.dto.search.secondarie.Ids

// OK
data class Episode(

    val season: Int,
    val number: Int,
    val title: String,
    val ids: Ids, // classe K
    val show: Show // classe K

) {

    // OK uguale altre
    fun imageUrl(): String = "http://img.omdbapi.com/?apikey=ef6d3d4c&i=${ids.imdb}"

}