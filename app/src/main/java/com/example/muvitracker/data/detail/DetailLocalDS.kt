package com.example.muvitracker.data.detail

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/*
 * GET - con Livedata read automatico || update e delete non necessario
 * getLivedataList() : MUtableLiveData
 * readCacheList() : List<DetailEntity>
 * ListFromJson() - conversione
 *
 * SET - create MutableList, setItem
 * saveNewItemInSharedList () - check id, poi aggiungi alla lista esistente
 *
 * solo lettura, unica modifica sharedPrefs ->
 */


class DetailLocalDS
private constructor(
    private val context: Context
) {
    private val gson = Gson()
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("detail_cache", Context.MODE_PRIVATE)


// GET DATA ####################################################################

    fun getLivedataList(): MutableLiveData<List<DetailEntity>> {
        val liveData = MutableLiveData<List<DetailEntity>>()

        liveData.value = loadListFromShared() // first update

        sharedPreferences.registerOnSharedPreferenceChangeListener { _, key ->
            // viene chiamata quando il valore (interno JSON) di qualsiasi "key" cambia
            if (key == DETAIL_KEY_01) {
                liveData.value =
                    loadListFromShared() // update onChange JSON contenuto nella key rispettiva
            }
        }
        return liveData
    }


    private fun loadListFromShared(): List<DetailEntity> {
        val json = sharedPreferences.getString(DETAIL_KEY_01, null) ?: ""
        return getListFromJson(json) // conversione
    }


    private fun getListFromJson(jsonString: String): List<DetailEntity> {
        var listType =
            object : TypeToken<List<DetailEntity>>() {}.type          // get il tipe token corretto
        var transformedList: List<DetailEntity> =
            gson.fromJson(jsonString, listType) ?: listOf()        // converti
        return transformedList
        // con gestione caso return null
    }


// SET ####################################################################


    // OK
    fun addOrUpdateItem(inputItem: DetailEntity) {
        val sharedList = loadListFromShared().toMutableList()
        val index = sharedList.indexOfFirst { sharedEntity ->
            sharedEntity.ids.trakt == inputItem.ids.trakt
        } // return indice condizione o -1

        if (index == -1) {
            sharedList.add(inputItem)
        } else {
            sharedList[index] = inputItem
        }

        saveListInSharedPreferences(sharedList)

    }


    private fun saveListInSharedPreferences(updatedList: List<DetailEntity>) {
        // update shared list
        sharedPreferences.edit()
            .putString(DETAIL_KEY_01, getJson(updatedList))
            .apply()
    }


    // old
    private fun getJson(list: List<DetailEntity>): String {
        return gson.toJson(list) ?: ""
    }
// new
//    private fun List<DetailEntity>.toJson(): String {
//        return this.toJson() ?: ""
//    }
//


// ####################################################################
    companion object {
        private var instance: DetailLocalDS? = null
        fun getInstance(context: Context): DetailLocalDS {
            if (instance == null) {
                instance = DetailLocalDS(context)
            }
            return instance!!
        }

        private const val DETAIL_KEY_01 = "detail_key_01"
    }
}


