package com.example.dchya24.submission4.model;

import com.example.dchya24.submission4.BuildConfig;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvShow {
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("created_by")
    @Expose
    private ArrayList<CreatedBy> createdBy = null;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("genres")
    @Expose
    private ArrayList<Genre> genres = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("last_air_date")
    @Expose
    private String lastAirDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("seasons")
    @Expose
    private ArrayList<Season> seasons = null;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w500" + backdropPath;
    }

    public ArrayList<CreatedBy> getCreatedBy() {
        return createdBy;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getPosterURL() {
        return BuildConfig.IMAGE_DB_BASE_URL +  posterPath;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }


    public String getVoteAverage() {
        return voteAverage.toString();
    }
}
