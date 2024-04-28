package com.example.muvitracker.myappunti.kotlin

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.muvitracker.R

/** contiene:
 * - EmptyStatesManagement class
 * - EmptyStatesEnum
 * - Visibility enum
 *
 * - EmptyStatesCallbackList
 * - EmptyStatesCallback
 */

class EmptyStatesManagement(private val context: Context) {

    companion object {
        var instance: EmptyStatesManagement? = null

        fun getInstance(context: Context): EmptyStatesManagement {
            if (instance == null) {
                instance = EmptyStatesManagement(context)
            }
            return instance!!
        }
    }


    // non serve privato
    val NO_INTERNET_MSG = context.getString(R.string.error_message_no_internet)
    val OTHER_ERROR_MSG = context.getString(R.string.error_message_other)


    // versione NUOVA - dati in locale
    // per popu e boxo
    fun emptyStatesFlow(
        emptyStates: EmptyStatesEnum,
        progressBar: View,
        errorMsgTextview: TextView
    ) {
        when (emptyStates) {

            EmptyStatesEnum.ON_START -> {
                setProgressBar(progressBar, Visibility.SHOW) // si, da mettere quello lineare
                setErrorMsg(errorMsgTextview, Visibility.HIDE)
            }

            EmptyStatesEnum.ON_FORCE_REFRESH -> {
                setProgressBar(progressBar, Visibility.HIDE) //no
                setErrorMsg(errorMsgTextview, Visibility.HIDE)
            }

            EmptyStatesEnum.ON_SUCCESS -> {
                setProgressBar(progressBar, Visibility.HIDE)
                setErrorMsg(errorMsgTextview, Visibility.HIDE)
            }

            EmptyStatesEnum.ON_ERROR_IO -> {
                setProgressBar(progressBar, Visibility.HIDE)
                setErrorMsg(errorMsgTextview, Visibility.SHOW, NO_INTERNET_MSG)
            }

            EmptyStatesEnum.ON_ERROR_OTHER -> {
                setProgressBar(progressBar, Visibility.HIDE)
                setErrorMsg(errorMsgTextview, Visibility.SHOW, OTHER_ERROR_MSG)
            }

        }
    }


    // per Deta
    // mostrarla solo quando success
    fun emptyStatesFlow(
        emptyStates: EmptyStatesEnum,
        insideScrollView: ViewGroup,
        progressBar: View,
        errorMsgTextview: TextView
    ) {
        when (emptyStates) {

            EmptyStatesEnum.ON_START -> {
                setInsideScrollVisibility(insideScrollView, Visibility.HIDE)
                setProgressBar(progressBar, Visibility.SHOW) // si, da mettere quello lineare
                setErrorMsg(errorMsgTextview, Visibility.HIDE)
            }

            EmptyStatesEnum.ON_FORCE_REFRESH -> {
                setInsideScrollVisibility(insideScrollView, Visibility.HIDE)
                setProgressBar(progressBar, Visibility.HIDE) //no
                setErrorMsg(errorMsgTextview, Visibility.HIDE)
            }

            EmptyStatesEnum.ON_SUCCESS -> {
                setInsideScrollVisibility(insideScrollView, Visibility.SHOW)
                setProgressBar(progressBar, Visibility.HIDE)
                setErrorMsg(errorMsgTextview, Visibility.HIDE)
            }

            EmptyStatesEnum.ON_ERROR_IO -> {
                setInsideScrollVisibility(insideScrollView, Visibility.HIDE)
                setProgressBar(progressBar, Visibility.HIDE)
                setErrorMsg(errorMsgTextview, Visibility.SHOW, NO_INTERNET_MSG)
            }

            EmptyStatesEnum.ON_ERROR_OTHER -> {
                setInsideScrollVisibility(insideScrollView, Visibility.HIDE)
                setProgressBar(progressBar, Visibility.HIDE)
                setErrorMsg(errorMsgTextview, Visibility.SHOW, OTHER_ERROR_MSG)
            }

        }
    }


    /** funzioni private -> utilizzate dentro
     *
     */
    // 1. OK nuovo
    private fun setProgressBar(
        progressBar: View,
        visibilita: Visibility
    ) {

        progressBar.visibility =
            if (visibilita == Visibility.SHOW)
                View.VISIBLE
            else
                View.GONE
    }

    // 2
    private fun setErrorMsg(
        errorMsgTextview: TextView,
        visibilita: Visibility,
        errorMsg: String = ""
    ) {
        errorMsgTextview.visibility =
            if (visibilita == Visibility.SHOW)
                View.VISIBLE
            else
                View.GONE

        errorMsgTextview.setText(errorMsg)
    }

    // 3
    private fun setInsideScrollVisibility(
        insideScrollView: ViewGroup,
        visibilita: Visibility
    ) {
        insideScrollView.visibility =
            if (visibilita == Visibility.SHOW) View.VISIBLE
            else View.GONE
    }


    private enum class Visibility {
        SHOW,
        HIDE
    }

}


enum class EmptyStatesEnum {
    ON_START, // local + start loading + no error
    ON_FORCE_REFRESH, // local, no loading, no error
    ON_SUCCESS, // server + stop loading + no error
    ON_ERROR_IO, // local + stop loading + error msg
    ON_ERROR_OTHER // // local + stop loading + error msg
}


interface EmptyStatesCallbackList<T> {
    fun OnStart()
    fun onSuccess(list: List<T>)
    fun onErrorIO()
    fun onErrorOther()
}


interface EmptyStatesCallback<T> {
    fun OnStart()
    fun onSuccess(obj: T)
    fun onErrorIO()
    fun onErrorOther()

}











