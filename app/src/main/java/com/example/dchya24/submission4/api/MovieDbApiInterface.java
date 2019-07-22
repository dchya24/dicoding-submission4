package com.example.dchya24.submission4.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MovieDbApiInterface {
    @GET("discover/movie")
    Call<ResponseBody> getDiscoverMovieJson(@QueryMap Map<String, String> options);

    @GET("discover/tv")
    Call<ResponseBody> getDiscoverTvJson(@QueryMap Map<String, String> options);

}
