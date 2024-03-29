package com.example.dchya24.submission4.views;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.dchya24.submission4.api.JsonApiResponse;
import com.example.dchya24.submission4.model.DiscoverMovie;
import com.example.dchya24.submission4.viewmodels.MovieDiscoverViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCatalogueFragment extends Fragment {
    private ProgressBar progressBar;
    private MovieListAdapter movieListAdapter;

    public MovieCatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_catalogue, container, false);
        progressBar = view.findViewById(R.id.pb_movie_catalogue);

        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.rv_movie_catalogue);

        // set up recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // recyclerView.setHasFixedSize(true);

        // initialize adapter
        movieListAdapter = new MovieListAdapter(getContext());
        recyclerView.setAdapter(movieListAdapter);
        progressBar.setVisibility(View.VISIBLE);

        // menginialisasi ViewModel
        MovieDiscoverViewModel movieDiscoverViewModel = ViewModelProviders.of(this).get(MovieDiscoverViewModel.class);
        movieDiscoverViewModel.getData().observe(this, getListMovie);
        return view;
    }

    private Observer<JsonApiResponse> getListMovie = new Observer<JsonApiResponse>() {
        @Override
        public void onChanged(JsonApiResponse jsonApiResponse) {
            if(jsonApiResponse == null){
                Toast.makeText(getContext(), "Something Error", Toast.LENGTH_LONG).show();
            }

            if(jsonApiResponse.getThrowable() == null){
                ArrayList<DiscoverMovie> discoverMovies = new ArrayList<>();
                try {
                    String data = jsonApiResponse.getResponseBody().string();
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray results = jsonObject.getJSONArray("results");

                    for(int i = 0; i < results.length(); i++){
                        DiscoverMovie discoverMovie = new DiscoverMovie(results.getJSONObject(i));
                        discoverMovies.add(discoverMovie);
                    }
                    movieListAdapter.setDiscoverMovies(discoverMovies);
                    showPogressBar(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{
                String activityName = getActivity().getClass().getSimpleName();
                String  errMessage =  jsonApiResponse.getThrowable().getMessage();
                Toast.makeText(getContext(), errMessage, Toast.LENGTH_LONG).show();
                showPogressBar(false);
                Log.e(activityName, errMessage);
            }
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
