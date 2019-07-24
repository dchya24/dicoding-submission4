package com.example.dchya24.submission4.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dchya24.submission4.R;


public class MovieFavoritFragment extends Fragment {
    private ProgressBar progressBar;

    public MovieFavoritFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_favorit, container, false);
        progressBar = view.findViewById(R.id.pb_movie_favorit);
        progressBar.setVisibility(View.VISIBLE);

        return view;
    }


}

