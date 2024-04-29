package com.example.muvitracker.inkotlin.ui.mainactivity.search

import com.example.muvitracker.inkotlin.data.dto.search.SearDto


interface SearContract {

     interface View {
         fun updateUi(list:List<SearDto>)
         fun startDetailsFragment(traktMovieId: Int)
     }

     interface Presenter {
         fun loadNetworkResult(text:String)
         fun onVHolderClick(traktMovieId: Int)
     }

}