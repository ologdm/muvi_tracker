package com.example.muvitracker.mainactivity.kotlin.sear

import com.example.muvitracker.repo.kotlin.dto.search.SearDto
import com.example.muvitracker.utils.kotlin.RetrofitCallbackList

class SearPresenter(val view: SearContract.View) : SearContract.Presenter {


/* eugi chiamata veloce
    init {
        getNetworkResult("dima")
    }

 */


    // OK
    override fun getNetworkResult(queryText: String) {

        SearRepo.getNetworkResult(queryText, object : RetrofitCallbackList<SearDto>{

            override fun onSuccess(serverList: List<SearDto>) {
                // aggiorna fragment
                view.updateUi(serverList)
            }

            override fun onError(throwable: Throwable) {
                // TODO: gestire - no results, no internet
            }

        })

    }


    override fun onVHolderClick(traktMovieId: Int) {
        view.startDetailsFragment(traktMovieId)
    }


}