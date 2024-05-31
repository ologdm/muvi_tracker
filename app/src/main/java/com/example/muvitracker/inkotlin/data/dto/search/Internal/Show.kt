package com.example.muvitracker.inkotlin.data.dto.search.Internal

import com.example.muvitracker.inkotlin.data.dto.support.Ids


// OK
data class Show(

    val title: String,
    val year: Int,
    val ids: Ids // classe K
) {

    fun imageUrl(): String = "http://img.omdbapi.com/?apikey=ef6d3d4c&i=${ids.imdb}"
}