package com.example.dchya24.submission4.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.example.dchya24.submission4.model.TvShowFavorite;

import java.util.List;

@Dao
public interface TvShowFavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTvShowFav(TvShowFavorite tvShowFavorite);

    @Query("SELECT * FROM tbtvshowfavorite ORDER BY 'date' DESC")
    List<TvShowFavorite> getTvShowFavorite();

    @Query("SELECT id FROM tbtvshowfavorite WHERE id = :id LIMIT 1")
    TvShowFavorite getTvShowFavoriteById(int id);

    @Delete
    int deleteTvShowFavorite(TvShowFavorite tvShowFavorite);

    @Query("SELECT * FROM tbtvshowfavorite ORDER BY 'date' DESC")
    Cursor selectAllTvShowFavorite();
}
