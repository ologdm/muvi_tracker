package com.example.muvitracker.data.prefs

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PrefsLocalDS @Inject constructor(
    private val gson: Gson,
    private val sharedPreferences: SharedPreferences
) {

    // GET ######################################################

    private val prefsChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == PREFS_KEY_01) {
                liveDataList.postValue(loadSharedList())
            }
        }

    val liveDataList: MutableLiveData<List<PrefsEntity>> by lazy {
        MutableLiveData<List<PrefsEntity>>().also {
            it.value = loadSharedList()
            sharedPreferences.registerOnSharedPreferenceChangeListener(prefsChangeListener)
        }
    }


    private fun loadSharedList(): List<PrefsEntity> {
        val json =
            sharedPreferences.getString(PREFS_KEY_01, "").orEmpty()
        return getListFromJson(json)
    }


    private fun getListFromJson(jsonString: String): List<PrefsEntity> {
        val listType = object : TypeToken<List<PrefsEntity>>() {}.type
        return gson.fromJson(jsonString, listType) ?: emptyList()
    }


    // SET ######################################################
    fun toggleFavoriteOnDB(movieId: Int) {
        synchronized(this) {
            val cache = loadSharedList().toMutableList()
            val index = cache.indexOfFirst { it.movieId == movieId }
            if (index != -1) {
                val current = cache[index]
                cache[index] = current.copy(liked = !current.liked)
            } else {
                cache.add(PrefsEntity(liked = true, watched = false, movieId = movieId))
            }
            saveListInShared(cache)
        }
    }


    fun updateWatchedOnDB(movieId: Int, watched: Boolean) {
        synchronized(this) { // TODO - test not necessary
            val cache = loadSharedList().toMutableList()
            val index = cache.indexOfFirst { it.movieId == movieId }
            if (index != -1) {
                val current = cache[index]
                cache[index] = current.copy(watched = watched)
            } else {
                cache.add(PrefsEntity(liked = false, watched = watched, movieId = movieId))
            }
            saveListInShared(cache)
        }
    }


    fun deleteItemFromDB(movieId: Int) {
        synchronized(this) {
            val cache = loadSharedList().toMutableList()
            val index = cache.indexOfFirst { it.movieId == movieId }
            if (index != -1) {
                cache.removeAt(index)
            }
            saveListInShared(cache)
        }
    }


// ###########################################################################


    private fun saveListInShared(list: List<PrefsEntity>) {
        synchronized(this) {
            sharedPreferences.edit()
                .putString(PREFS_KEY_01, getJson(list))
                .commit()
        }
    }


    private fun getJson(list: List<PrefsEntity>): String {
        return gson.toJson(list) ?: ""
    }


    companion object {
        private const val PREFS_KEY_01 = "prefs_key_01"
    }

}
