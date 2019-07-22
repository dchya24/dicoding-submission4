package com.example.dchya24.submission4.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dchya24.submission4.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowCatalogueFragment extends Fragment {
    private ProgressBar progressBar;

    public TvShowCatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show_catalogue, container, false);
        progressBar = view.findViewById(R.id.pb_tv_catalogue);
        progressBar.setVisibility(View.VISIBLE);

        return view;
    }

}
