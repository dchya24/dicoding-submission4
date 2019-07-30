package com.example.dchya24.submission4.model;

import com.example.dchya24.submission4.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class DiscoverMovie {
    private int id;
    private String title;
    private String overview;
    private String poster_path;
    private String release_date;
    private String date;

    public DiscoverMovie(JSONObject jsonObject) {
        try{
            this.id = jsonObject.getInt("id");
            this.title = jsonObject.getString("title");
            this.overview = jsonObject.getString("overview");
            this.poster_path = jsonObject.getString("poster_path");
            this.release_date = jsonObject.getString("release_date");

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public String getPosterUrl() {
        return BuildConfig.IMAGE_DB_BASE_URL + poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
