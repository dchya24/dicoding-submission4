package com.example.dchya24.submission4.model;

import com.example.dchya24.submission4.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class DiscoverMovie {
    private int id;
    private String title, overview, poster_path, release_date;

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

    public String getPoster_path() {
        return BuildConfig.IMAGE_DB_BASE_URL + poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }
}
