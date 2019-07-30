package com.example.dchya24.submission4.model;

import com.example.dchya24.submission4.BuildConfig;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Season {
    @SerializedName("air_date")
    @Expose
    private String airDate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("season_number")
    @Expose
    private Integer seasonNumber;

    public String getAirDate() {
        return airDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return BuildConfig.IMAGE_DB_BASE_URL + posterPath;
    }
}
