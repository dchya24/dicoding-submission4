package com.example.dchya24.submission4.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.dchya24.submission4.BuildConfig;
import com.example.dchya24.submission4.api.MovieDbApiInterface;
import com.example.dchya24.submission4.api.MovieDbApiService;
import com.example.dchya24.submission4.api.TvShowDetailApiResponse;
import com.example.dchya24.submission4.database.AppDatabase;
import com.example.dchya24.submission4.model.TvShow;
import com.example.dchya24.submission4.support.FavoriteAsyncTask;
import com.example.dchya24.submission4.support.FavoriteInterface;
import com.example.dchya24.submission4.support.FavoriteSupport;
import com.example.dchya24.submission4.support.MyAsyncCallback;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailRepository implements MyAsyncCallback {
    private MovieDbApiInterface movieDbApiInterface;
    private AppDatabase db;
    private WeakReference<FavoriteInterface> reference;

    public TvShowDetailRepository(Context context, FavoriteInterface favoriteInterface){
        this.movieDbApiInterface = MovieDbApiService.getClient();
        this.db = Room.databaseBuilder(context, AppDatabase.class, "entertaimentdb").build();
        reference = new WeakReference<>(favoriteInterface);
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

    public void executeFavoriteData(FavoriteSupport favoriteSupport){
        FavoriteAsyncTask favoriteAsyncTask = new FavoriteAsyncTask(this, db);
        favoriteAsyncTask.execute(favoriteSupport);
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(FavoriteSupport favoriteSupport) {
        reference.get().setFavoriteData(favoriteSupport);
    }
}
