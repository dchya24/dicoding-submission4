package com.example.dchya24.submission4.repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.dchya24.submission4.BuildConfig;
import com.example.dchya24.submission4.api.MovieDbApiInterface;
import com.example.dchya24.submission4.api.MovieDbApiService;
import com.example.dchya24.submission4.api.TvShowDetailApiResponse;
import com.example.dchya24.submission4.model.TvShow;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailApiRepository {
    private MovieDbApiInterface movieDbApiInterface;

    public TvShowDetailApiRepository(){
        this.movieDbApiInterface = MovieDbApiService.getClient();
    }

    public MutableLiveData<TvShowDetailApiResponse> getTvShowDetail(int id){
        final MutableLiveData<TvShowDetailApiResponse> mutableLiveData = new MutableLiveData<>();
        final Map<String, String> map = new HashMap<>();
        map.put("api_key", BuildConfig.API_KEY);
        map.put("language", "en-US");

        Call<TvShow> tvShowCall = movieDbApiInterface.getTvShowDetail(id, map);
        tvShowCall.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                if(response.isSuccessful()){
                    mutableLiveData.postValue(new TvShowDetailApiResponse(response.body()));
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                mutableLiveData.postValue(new TvShowDetailApiResponse(t));
            }
        });

        return mutableLiveData;
    }
}
