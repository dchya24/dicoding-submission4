package com.example.dchya24.submission4.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.adapter.MovieListAdapter;
import com.example.dchya24.submission4.model.DiscoverMovie;
import com.example.dchya24.submission4.support.SwipeToDeleteCallback;
import com.example.dchya24.submission4.viewmodels.MovieFavoriteViewModel;

import java.util.ArrayList;


public class MovieFavoriteFragment extends Fragment {
    private ProgressBar progressBar;
    private MovieListAdapter movieListAdapter;
    private FrameLayout frameLayout;
    private RecyclerView rvFavMovie;

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_favorit, container, false);
        rvFavMovie = view.findViewById(R.id.rv_movie_favorit);
        progressBar = view.findViewById(R.id.pb_movie_favorit);
        frameLayout = view.findViewById(R.id.frame_movie_favorite);

        progressBar.setVisibility(View.VISIBLE);

        movieListAdapter = new MovieListAdapter(getContext());
        rvFavMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFavMovie.setAdapter(movieListAdapter);

        MovieFavoriteViewModel viewModel = ViewModelProviders.of(this).get(MovieFavoriteViewModel.class);
        viewModel.setMovieFavorite();
        viewModel.getMovieMutableLiveData().observe(this, getListData);

        enableSwipeToDeleteAndUndo();

        return view;
    }

    private Observer<ArrayList<DiscoverMovie>> getListData = new Observer<ArrayList<DiscoverMovie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<DiscoverMovie> discoverMovies) {
            movieListAdapter.setDiscoverMovies(discoverMovies);
            progressBar.setVisibility(View.GONE);
        }
    };

    private void enableSwipeToDeleteAndUndo(){
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getActivity()){
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i){
                final int position = viewHolder.getAdapterPosition();
                final DiscoverMovie discoverMovie = movieListAdapter.getData(position);

                movieListAdapter.removeItem(position, discoverMovie);

                Snackbar snackbar = Snackbar.make(frameLayout,
                        getResources().getString(R.string.has_remove_movie_favorite), Snackbar.LENGTH_LONG);

                snackbar.setAction("Undo", new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        movieListAdapter.restoreItem(discoverMovie, position);
                        rvFavMovie.scrollToPosition(position);
                    }
                });

                snackbar.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(rvFavMovie);
    }

}

