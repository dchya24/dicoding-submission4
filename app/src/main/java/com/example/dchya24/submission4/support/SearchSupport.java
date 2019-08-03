package com.example.dchya24.submission4.support;

import com.example.dchya24.submission4.model.DiscoverMovie;
import com.example.dchya24.submission4.model.DiscoverTvShow;

import java.util.ArrayList;

public class SearchSupport {
    private ArrayList<DiscoverMovie> discoverMovies = new ArrayList<>();
    private ArrayList<DiscoverTvShow> discoverTvShows = new ArrayList<>();

    public ArrayList<DiscoverMovie> getDiscoverMovies() {
        return discoverMovies;
    }

    public void addDiscoverMovie(DiscoverMovie discoverMovies) {
        this.discoverMovies.add(discoverMovies);
    }

    public ArrayList<DiscoverTvShow> getDiscoverTvShows() {
        return discoverTvShows;
    }

    public void addDiscoverTvShow(DiscoverTvShow discoverTvShows) {
        this.discoverTvShows.add(discoverTvShows);
    }

    public void clearData(){
        this.discoverMovies.clear();
        this.discoverTvShows.clear();
    }
}
