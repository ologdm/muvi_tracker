package com.example.muvitracker.inkotlin.model.dto

import com.example.muvitracker.inkotlin.model.dto.support.Ids

// serializable non e da implementare
// parcelable solo per inviare dati in android (intent, tra fragment ecc)

// solo getters

// -> uso per Details e prefList


// OK
data class DetaDto(

    //val type: String = "", // valore standard

    // valori predefiniti costruttore

    // elementi controllo prefList
    val liked: Boolean = false,
    val watched: Boolean = false,


    val title: String,
    val year: Int,
    val ids: Ids,

    // altro used only 6, TODO aggiungere genres
    //val tagline: String = "",
    val overview: String = "",
    val released: String = "",
    val runtime: Int = 0,
    val country: String? = "",
    //@SerializedName("updated_at")
    //val updatedAt: String = "",
    //trailer = null
    //val homepage: String = "",
    //val status: String = "",
    val rating: Float = 0F,
    //val votes: Int =0,
    //@SerializedName("comment_count")
    //val commentCount: Int =0,
    //val language: String ="",
    //val availableTranslations: List<String> = listOf(),
    val genres: List<String> = emptyList(),
    //val certification: String = ""
) {


    fun getImageUrl(): String {
        return "http://img.omdbapi.com/" + "?apikey=ef6d3d4c" + "&i=${ids.imdb}";
    }



}


// ####################################################


// JSON Details - Trakt Summary -> https://trakt.docs.apiary.io/#reference/sync/update-favorite-item/get-a-movie

// API GET:  https://private-anon-9505e163f3-trakt.apiary-mock.com/movies/ (id)
// (id) --> Trakt ID, Trakt , or IMDB ID Example: tron-legacy-2010.

// ** titolo: TRON: Legacy /-> slug - tron-legacy-2010

// ######################################

/* /movies/tron-legacy-2010
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
 */


// #######################################
/* /movies/tron-legacy-2010?extended=full
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











