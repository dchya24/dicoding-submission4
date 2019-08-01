package com.example.dchya24.submission4.support;

import com.example.dchya24.submission4.model.MovieFavorite;
import com.example.dchya24.submission4.model.TvShowFavorite;

import java.util.List;

public class FavoriteSupport {
    /**
     * 201 = get All movie Favorite List
     * 202 = insert MovieFavorite Data
     * 203 = Delete Data
     * 204 = Get one movie favorite
     * 211 = get All TvShow Favorite List
     * 212 = Insert TvShowFavorite Data
     * 213 = Delete TvShowFavorite
     * 214 = get One TvShowFavorite
     */

    private int STATUS_REQUEST;
    private int item_id;

    private MovieFavorite movieFavorite;
    private List<MovieFavorite> movieFavoriteList;
    private TvShowFavorite tvShowFavorite;
    private List<TvShowFavorite> tvShowFavoriteList;


    /**
     * List Of Constructor
     */
    public FavoriteSupport(int i, MovieFavorite movieFavorite){
        this.STATUS_REQUEST = i;
        this.movieFavorite = movieFavorite;
    }

    public FavoriteSupport(int i, TvShowFavorite tvShowFavorite){
        this.STATUS_REQUEST = i;
        this.tvShowFavorite = tvShowFavorite;
    }

    public FavoriteSupport(int i){
        this.STATUS_REQUEST = i;
    }

    /**
     * End
     */

    public int getStatusRequest() {
        return STATUS_REQUEST;
    }

    public MovieFavorite getMovieFavorite() {
        return movieFavorite;
    }

    public List<MovieFavorite> getMovieFavoriteList() {
        return movieFavoriteList;
    }

    public void setMovieFavoriteList(List<MovieFavorite> movieFavoriteList) {
        this.movieFavoriteList = movieFavoriteList;
    }

    public void setMovieFavorite(MovieFavorite movieFavorite) {
        this.movieFavorite = movieFavorite;
    }

    public void setStatusInsert(Long status) {
        Long insert_status = status;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setDeleteStatus(int delete_status) {
        int delete_status1 = delete_status;
    }

    public TvShowFavorite getTvShowFavorite() {
        return tvShowFavorite;
    }

    public void setTvShowFavorite(TvShowFavorite tvShowFavorite) {
        this.tvShowFavorite = tvShowFavorite;
    }

    public List<TvShowFavorite> getTvShowFavoriteList() {
        return tvShowFavoriteList;
    }

    public void setTvShowFavoriteList(List<TvShowFavorite> tvShowFavoriteList) {
        this.tvShowFavoriteList = tvShowFavoriteList;
    }
}
