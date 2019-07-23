package com.example.dchya24.submission4.repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.dchya24.submission4.BuildConfig;
import com.example.dchya24.submission4.api.JsonApiResponse;
import com.example.dchya24.submission4.api.MovieDbApiInterface;
import com.example.dchya24.submission4.api.MovieDbApiService;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDiscoverApiRepository {
    public MovieDbApiInterface movieDbApiInterface;

    public  MovieDiscoverApiRepository(){
        movieDbApiInterface = MovieDbApiService.getClient();
    }

    public MutableLiveData<JsonApiResponse> getMovieDiscover(){
        Map<String, String> map = new HashMap<>();
        map.put("api_key", BuildConfig.API_KEY);
        map.put("language", "en-US");

        final MutableLiveData<JsonApiResponse> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ResponseBody> responseBodyCall = movieDbApiInterface.getDiscoverMovieJson(map);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    arrayListMutableLiveData.postValue(new JsonApiResponse(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                arrayListMutableLiveData.postValue(new JsonApiResponse(t));
            }
        });

        return  arrayListMutableLiveData;
    }
}
