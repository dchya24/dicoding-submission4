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

public class TvShowDiscoverApiRepository {
    public MovieDbApiInterface movieDbApiInterface;

    public TvShowDiscoverApiRepository(){
        movieDbApiInterface = MovieDbApiService.getClient();
    }

    public MutableLiveData<JsonApiResponse> getDiscoverTvShow(){
        final MutableLiveData<JsonApiResponse> apiResponseMutableLiveData = new MutableLiveData<>();
        Map<String, String> map = new HashMap<>();
        map.put("api_key", BuildConfig.API_KEY);
        map.put("language", "en-US");

        Call<ResponseBody> responseBodyCall =  movieDbApiInterface.getDiscoverTvJson(map);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    apiResponseMutableLiveData.postValue(new JsonApiResponse(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                apiResponseMutableLiveData.postValue(new JsonApiResponse(t));
            }
        });

        return apiResponseMutableLiveData;

    }
}
