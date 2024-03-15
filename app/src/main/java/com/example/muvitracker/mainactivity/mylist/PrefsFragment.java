package com.example.muvitracker.mainactivity.mylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muvitracker.R;
import com.example.muvitracker.mainactivity.MainNavigator;
import com.example.muvitracker.repo.dto.DetailsDto;

import java.util.List;


// StartDetails - funzione Semplificata, spostata su navigator

public class PrefsFragment extends Fragment implements PrefsContract.View {


    // 1. ATTRIBUTI
    RecyclerView recyclerView;
    PrefsAdapter adapter = new PrefsAdapter();

    PrefsPresenter presenter = new PrefsPresenter(this);
    MainNavigator navigator = new MainNavigator();

    // 2. COSTRUTTORE no


    // 3. FRAGMENT

    // 3.1 Creazione
    @Nullable
    @Override
    public android.view.View onCreateView(
        @NonNull LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_prefs, container, false);
    }


    // 3.2 Logica
    @Override
    public void onViewCreated(
        @NonNull android.view.View view,
        @Nullable Bundle savedInstanceState
    ) {

        recyclerView = view.findViewById(R.id.mylistRV);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        //adapter = new PrefsAdapter(/*this.requireContext()*/);


        // Click VH
        // Funzione Semplificata
        adapter.setCallbackVH(traktMovieId -> {
            navigator.startDetailsFragmentAndAddToBackstack(requireActivity(), traktMovieId);
        });


        // Click Checkbox - watched
        adapter.setCallbackCheckbox(listAdapter -> {
            presenter.setList(listAdapter);
            //presenter.loadData();
        });


        // TODO Click liked
        adapter.setCallbackLiked(dto -> {
            // aggiorno repo
            presenter.toggleFavorite(dto);
            // aggiorno Ui
            //presenter.loadData();
        });


        presenter.loadData(); // contiene view.updateUi

    }


    // 4. CONTRACT
    // 4.1
    @Override
    public void updateUi(List<DetailsDto> prefsList) {
        adapter.updateList(prefsList);
    }


}