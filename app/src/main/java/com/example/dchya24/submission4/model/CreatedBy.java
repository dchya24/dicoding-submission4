package com.example.dchya24.submission4.model;

import com.example.dchya24.submission4.BuildConfig;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatedBy {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfilePath() {
        return BuildConfig.IMAGE_DB_BASE_URL + profilePath;
    }
}
