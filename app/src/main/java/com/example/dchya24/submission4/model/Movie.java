package com.example.dchya24.submission4.model;

import com.example.dchya24.submission4.BuildConfig;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movie {
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("genres")
    @Expose
    private ArrayList<Genre> genres = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    public String getBackdropPath() {
        return BuildConfig.IMAGE_DB_BASE_URL + backdropPath;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return BuildConfig.IMAGE_DB_BASE_URL + posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


    public int getRuntime() {
        return ((runtime != null) ? runtime : 0);
    }

    public String getTitle() {
        return title;
    }

    public String getVoteAverage() {
        return voteAverage.toString();
    }
}
