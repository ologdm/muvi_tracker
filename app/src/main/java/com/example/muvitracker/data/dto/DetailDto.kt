package com.example.muvitracker.data.dto

import com.example.muvitracker.data.detail.DetailEntity
import com.example.muvitracker.data.dto.base.Ids


data class DetailDto(

    val liked: Boolean,
    val watched: Boolean,

    val title: String,
    val year: Int,
    val ids: Ids,

    // val default per elementi che possono mancare
    val overview: String = "",
    val released: String = "",
    val runtime: Int = 0,
    val country: String? = "",
    val rating: Float = 0F,
    val genres: List<String> = emptyList(),
)


fun DetailDto.toEntity(): DetailEntity {
    return DetailEntity(
        title = title,
        year = year,
        ids = ids,
        overview = overview,
        released = released,
        runtime = runtime,
        country = country ?: "",
        rating = rating,
        genres = genres
    )
}


/*
* JSON Details ####################################################
*
* Trakt Summary -> https://trakt.docs.apiary.io/#reference/movies/summary
*
* API GET:  movies/id
* id -> Trakt ID, Trakt slug, or IMDB ID Example: tron-legacy-2010.
* id -> path


// 1. short

 /movies/tron-legacy-2010
{
  "title": "TRON: Legacy",
  "year": 2010,
  "ids": {
    "trakt": 1,
    "slug": "tron-legacy-2010",
    "imdb": "tt1104001",
    "tmdb": 20526
  }
}


// 2. extended
*  /movies/tron-legacy-2010?extended=full
{
    "title": "TRON: Legacy",
    "year": 2010,
    "ids": {
        "trakt": 343,
        "slug": "tron-legacy-2010",
        "imdb": "tt1104001",
        "tmdb": 20526
    },
    "tagline": "The Game Has Changed.",
    "overview": "Sam Flynn, the tech-savvy and daring son of Kevin Flynn, investigates his father's disappearance and is pulled into The Grid. With the help of  a mysterious program named Quorra, Sam quests to stop evil dictator Clu from crossing into the real world.",
    "released": "2010-12-16",
    "runtime": 125,
    "country": "us",
    "updated_at": "2014-07-23T03:21:46.000Z",
    "trailer": null,
    "homepage": "http://disney.go.com/tron/",
    "status": "released",
    "rating": 8,
    "votes": 111,
    "comment_count": 92,
    "language": "en",
    "available_translations": [
        "en"
    ],
    "genres": [
        "action"
    ],
    "certification": "PG-13"
}

 */
