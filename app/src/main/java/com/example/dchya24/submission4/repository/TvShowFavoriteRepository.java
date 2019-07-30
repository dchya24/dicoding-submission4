package com.example.dchya24.submission4.repository;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.dchya24.submission4.database.AppDatabase;
import com.example.dchya24.submission4.support.FavoriteAsyncTask;
import com.example.dchya24.submission4.support.FavoriteInterface;
import com.example.dchya24.submission4.support.FavoriteSupport;
import com.example.dchya24.submission4.support.MyAsyncCallback;

import java.lang.ref.WeakReference;

public class TvShowFavoriteRepository implements MyAsyncCallback {
    private AppDatabase db;
    private FavoriteSupport support;
    private WeakReference<FavoriteInterface> reference;

    public TvShowFavoriteRepository(Context context, FavoriteInterface favoriteInterface){
        db = Room.databaseBuilder(context, AppDatabase.class, "entertaimentdb")
                .allowMainThreadQueries().build();
        reference = new WeakReference<>(favoriteInterface);
    }

    public void setTvShowFavList(){
        FavoriteAsyncTask favoriteAsyncTask = new FavoriteAsyncTask(this, db);
        FavoriteSupport favoriteSupport = new FavoriteSupport(211);
        favoriteAsyncTask.execute(favoriteSupport);
    }

    public FavoriteSupport getSupport() {
        return support;
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(FavoriteSupport favoriteSupport) {
        reference.get().setFavoriteData(favoriteSupport);
        this.support = favoriteSupport;
    }

}
