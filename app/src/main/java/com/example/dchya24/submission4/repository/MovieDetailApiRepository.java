package com.example.dchya24.submission4.repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.dchya24.submission4.BuildConfig;
import com.example.dchya24.submission4.api.MovieDbApiInterface;
import com.example.dchya24.submission4.api.MovieDbApiService;
import com.example.dchya24.submission4.api.MovieDetailApiResponse;
import com.example.dchya24.submission4.model.Movie;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailApiRepository {
    private MovieDbApiInterface movieDbApiInterface;

    public MovieDetailApiRepository() {
        this.movieDbApiInterface = MovieDbApiService.getClient();
    }

    public MutableLiveData<MovieDetailApiResponse> getMovieDetail(int id){
        final MutableLiveData<MovieDetailApiResponse> mutableLiveData = new MutableLiveData<>();
        final Map<String, String> map = new HashMap<>();
        map.put("api_key", BuildConfig.API_KEY);
        map.put("language", "en-US");

        Call<Movie> movieCall = movieDbApiInterface.getMovieDetail(id, map);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(response.isSuccessful()){
                    mutableLiveData.postValue(new MovieDetailApiResponse(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                mutableLiveData.postValue(new MovieDetailApiResponse(t));
            }
        });

        return mutableLiveData;
    }

}
