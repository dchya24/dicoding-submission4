package com.example.dchya24.submission4.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.adapter.TvShowListAdapter;
import com.example.dchya24.submission4.model.DiscoverTvShow;
import com.example.dchya24.submission4.viewmodel.TvShowDiscoverViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowCatalogueFragment extends Fragment {
    private ProgressBar progressBar;
    private RecyclerView rvTvShow;
    private TvShowListAdapter tvShowListAdapter;


    public TvShowCatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show_catalogue, container, false);
        progressBar = view.findViewById(R.id.pb_tv_catalogue);
        rvTvShow = view.findViewById(R.id.rv_tv_catalogue);

        // set up recycler view
        rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTvShow.setHasFixedSize(true);

        showPogressBar(true);

        // set up adapter
        tvShowListAdapter = new TvShowListAdapter(getContext());

        // deklarasi view model
        TvShowDiscoverViewModel tvShowDiscoverViewModel = ViewModelProviders.of(this).get(TvShowDiscoverViewModel.class);
        tvShowDiscoverViewModel.setArrayListMutableLiveData();

        // cek keberhasilan mengambil data dari API
        if(tvShowDiscoverViewModel.getStatus_code() != 200){
            Toast.makeText(getContext(), tvShowDiscoverViewModel.getStatus_message(), Toast.LENGTH_SHORT).show();
        }else{
            tvShowDiscoverViewModel.getArrayListMutableLiveData().observe(this, getListTvDiscover);
        }

        return view;
    }

    private Observer<ArrayList<DiscoverTvShow>> getListTvDiscover = new Observer<ArrayList<DiscoverTvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<DiscoverTvShow> discoverTvShows) {
            tvShowListAdapter.setdIscoverTvShowArrayList(discoverTvShows);
            rvTvShow.setAdapter(tvShowListAdapter);
            showPogressBar(false);
        }
    };

    public void showPogressBar(boolean options){
        if(options){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

}
