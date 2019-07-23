package com.example.dchya24.submission4.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.dchya24.submission4.BuildConfig;
import com.example.dchya24.submission4.api.MovieDbApiInterface;
import com.example.dchya24.submission4.api.MovieDbApiService;
import com.example.dchya24.submission4.model.DiscoverTvShow;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDiscoverViewModel extends ViewModel {
    private MutableLiveData<ArrayList<DiscoverTvShow>> arrayListMutableLiveData = new MutableLiveData<>();
    private int status_code = 200;
    private String status_message = "status message";

    public MutableLiveData<ArrayList<DiscoverTvShow>> getArrayListMutableLiveData() {
        return arrayListMutableLiveData;
    }

    public void setArrayListMutableLiveData() {
        final ArrayList<DiscoverTvShow> discoverTvShows = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("api_key", BuildConfig.API_KEY);
        map.put("language", "en-US");

        MovieDbApiInterface movieDbApiInterface = MovieDbApiService.getClient().create(MovieDbApiInterface.class);
        Call<ResponseBody> responseBodyCall = movieDbApiInterface.getDiscoverTvJson(map);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    String string = response.body().string();
                    JSONObject jsonObject = new JSONObject(string);
                    JSONArray results = jsonObject.getJSONArray("results");

                    for(int i = 0; i < results.length(); i++){
                        DiscoverTvShow discoverTvShow = new DiscoverTvShow(results.getJSONObject(i));

                        discoverTvShows.add(discoverTvShow);
                    }

                    arrayListMutableLiveData.postValue(discoverTvShows);

                }catch (Exception e){
                    e.printStackTrace();
                    status_code = 400;
                    status_message = e.getMessage();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                status_code = 400;
                status_message = t.getMessage();
            }
        });

    }

    public int getStatus_code() {
        return status_code;
    }

    public String getStatus_message() {
        return status_message;
    }
}
