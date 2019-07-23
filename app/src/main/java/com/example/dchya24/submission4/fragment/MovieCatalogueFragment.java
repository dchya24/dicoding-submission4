package com.example.dchya24.submission4.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.adapter.MovieListAdapter;
import com.example.dchya24.submission4.model.DiscoverMovie;
import com.example.dchya24.submission4.viewmodel.MovieDiscoverViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCatalogueFragment extends Fragment {
    private ProgressBar progressBar;
    private MovieDiscoverViewModel movieDiscoverViewModel;
    private RecyclerView recyclerView;
    private MovieListAdapter movieListAdapter;

    public MovieCatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_catalogue, container, false);
        progressBar = view.findViewById(R.id.pb_movie_catalogue);
        recyclerView = view.findViewById(R.id.rv_movie_catalogue);

        // set up recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        // initialize adapter
        movieListAdapter = new MovieListAdapter(getContext());

        progressBar.setVisibility(View.VISIBLE);

        // menginialisasi ViewModel
        movieDiscoverViewModel = ViewModelProviders.of(this).get(MovieDiscoverViewModel.class);
        movieDiscoverViewModel.setMutableLiveData();

        // check status
        if(movieDiscoverViewModel.getStatus_code() != 200){
            Log.d("ERROR", movieDiscoverViewModel.getStatus_message());
            Toast.makeText(getContext(),movieDiscoverViewModel.getStatus_message(), Toast.LENGTH_LONG).show();
        }else{
            movieDiscoverViewModel.getMutableLiveData().observe(this, getListMovie);
        }

        return view;
    }

    private Observer<ArrayList<DiscoverMovie>> getListMovie = new Observer<ArrayList<DiscoverMovie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<DiscoverMovie> discoverMovies) {
            if(discoverMovies.size() > 0){
                movieListAdapter.setDiscoverMovies(discoverMovies);
                recyclerView.setAdapter(movieListAdapter);
                progressBar.setVisibility(View.GONE);
            }else{
                Toast.makeText(getContext(), "Tidak ada data", Toast.LENGTH_LONG).show();
            }
        }
    };

}
