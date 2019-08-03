package com.example.dchya24.submission4.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.example.dchya24.submission4.model.MovieFavorite;

import java.util.List;

@Dao
public interface MovieFavoritDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMovieFav(MovieFavorite movieFavorite);

    @Query("SELECT * FROM tbmoviefavorite ORDER BY 'date' DESC")
    List<MovieFavorite> getMovieFavorite();


    @Query("SELECT id FROM tbmoviefavorite WHERE id = :id LIMIT 1")
    MovieFavorite getMovieFavoriteId(int id);

    @Delete
    int deleteMovieFavorite(MovieFavorite movieFavorite);

    @Query("SELECT * FROM tbmoviefavorite ORDER BY 'date' DESC")
    Cursor selectAllMovieFavorite();

}
