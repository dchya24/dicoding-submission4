package com.example.dchya24.submission4.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.dchya24.submission4.dao.MovieFavoritDAO;
import com.example.dchya24.submission4.dao.TvShowFavoriteDAO;
import com.example.dchya24.submission4.model.MovieFavorite;
import com.example.dchya24.submission4.model.TvShowFavorite;

@Database(entities = {MovieFavorite.class, TvShowFavorite.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieFavoritDAO movieFavoritDAO();
    public abstract TvShowFavoriteDAO tvShowFavoriteDAO();
}
