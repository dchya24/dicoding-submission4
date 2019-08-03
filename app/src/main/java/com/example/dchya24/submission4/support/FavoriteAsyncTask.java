package com.example.dchya24.submission4.support;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dchya24.submission4.database.AppDatabase;

import java.lang.ref.WeakReference;

public class FavoriteAsyncTask extends AsyncTask<FavoriteSupport, FavoriteSupport, FavoriteSupport> {
    private static final String LOG_ASYNC = "Favorit AsyncTask";
    private AppDatabase db;
    private WeakReference<MyAsyncCallback> myAsyncCallbackWeakReference;

    public FavoriteAsyncTask(MyAsyncCallback myAsyncCallback, AppDatabase db){
        this.db = db;
        this.myAsyncCallbackWeakReference = new WeakReference<>(myAsyncCallback);
    }

    @Override
    protected FavoriteSupport doInBackground(FavoriteSupport... favoriteSupports) {
        FavoriteSupport fSupport = favoriteSupports[0];

        switch (fSupport.getStatusRequest()){
            case 201:
                fSupport.setMovieFavoriteList(db.movieFavoritDAO().getMovieFavorite());
                return fSupport;
            case 202:
                db.movieFavoritDAO().insertMovieFav(fSupport.getMovieFavorite());
                return fSupport;
            case 203:
                db.movieFavoritDAO().deleteMovieFavorite(fSupport.getMovieFavorite());
                return fSupport;
            case 204:
                fSupport.setMovieFavorite(db.movieFavoritDAO().getMovieFavoriteId(fSupport.getItem_id()));
                return fSupport;
            case 211:
                fSupport.setTvShowFavoriteList(db.tvShowFavoriteDAO().getTvShowFavorite());
                return fSupport;
            case 212:
                db.tvShowFavoriteDAO().insertTvShowFav(fSupport.getTvShowFavorite());
                return fSupport;
            case 213:
                db.tvShowFavoriteDAO().deleteTvShowFavorite(fSupport.getTvShowFavorite());
                return fSupport;
            case 214:
                fSupport.setTvShowFavorite(db.tvShowFavoriteDAO().getTvShowFavoriteById(fSupport.getItem_id()));
                return fSupport;
        }

        return fSupport;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        Log.d(LOG_ASYNC, "Wait.. onPreExecute");

        MyAsyncCallback myAsyncCallback = this.myAsyncCallbackWeakReference.get();
        if(myAsyncCallback != null) myAsyncCallback.onPreExecute();
    }

    @Override
    protected void onPostExecute(FavoriteSupport favoriteSupport){
        Log.d(LOG_ASYNC, "Done.. onPostExecute");
        myAsyncCallbackWeakReference.get().onPostExecute(favoriteSupport);
    }
}