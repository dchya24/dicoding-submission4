package com.example.dchya24.submission4.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.dchya24.submission4.BuildConfig;
import com.example.dchya24.submission4.api.MovieDbApiService;
import com.example.dchya24.submission4.api.MovieDbApiInterface;
import com.example.dchya24.submission4.model.DiscoverMovie;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDiscoverViewModel extends ViewModel {
    private MutableLiveData<ArrayList<DiscoverMovie>> mutableLiveData = new MutableLiveData<>();
    private int status_code = 200;
    private String status_message = "status message";

    public MutableLiveData<ArrayList<DiscoverMovie>> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setMutableLiveData(){
        Map<String, String> map = new HashMap<>();
        map.put("api_key", BuildConfig.API_KEY);
        map.put("language", "en-US");

        final ArrayList<DiscoverMovie> discoverMovies = new ArrayList<>();
        MovieDbApiInterface movieDbApiInterface = MovieDbApiService.getClient().create(MovieDbApiInterface.class);
        Call<ResponseBody> bodyCall = movieDbApiInterface.getDiscoverMovieJson(map);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    String responseData = response.body().string();

                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray results = jsonObject.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        DiscoverMovie discoverMovie = new DiscoverMovie(results.getJSONObject(i));
                        discoverMovies.add(discoverMovie);
                    }

                    mutableLiveData.postValue(discoverMovies);

                }catch (Exception e){
                    e.printStackTrace();
                    status_message = e.getMessage();
                    status_code = 500;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                status_message = t.getMessage();
                status_code = 500;
            }
        });
    }

    public int getStatus_code(){
        return status_code;
    }

    public String getStatus_message(){
        return status_message;
    }
}
