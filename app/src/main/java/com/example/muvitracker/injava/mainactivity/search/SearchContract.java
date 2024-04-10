package com.example.muvitracker.injava.mainactivity.search;

import com.example.muvitracker.injava.repo.dto.search.SearchDto;

import java.util.List;

public interface SearchContract {


    public interface View {

        // aggiorna adapter
        public void updateUi(List<SearchDto> list);

        // crea details
        public void startDetailsFragment(int id);

    }


    public interface Presenter {

        // callback di retrofit
        public void callServerAndUpdateUi();

        // comportamento al click oggetto
        public void onVHolderClick(int id);

        // passa stringa ricerca
        public void setSearchText(String searchText);

    }

}


