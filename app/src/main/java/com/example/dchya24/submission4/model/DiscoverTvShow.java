package com.example.dchya24.submission4.model;

import com.example.dchya24.submission4.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class DiscoverTvShow {
    private int id;
    private String name, overview, first_air_date, poster_path;

    public DiscoverTvShow(JSONObject jsonObject){
        try{
            this.id = jsonObject.getInt("id");
            this.name = jsonObject.getString("name");
            this.overview = jsonObject.getString("overview");
            this.first_air_date = jsonObject.getString("first_air_date");
            this.poster_path = jsonObject.getString("poster_path");

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public String getPoster_path() {
        return BuildConfig.IMAGE_DB_BASE_URL + poster_path;
    }
}
