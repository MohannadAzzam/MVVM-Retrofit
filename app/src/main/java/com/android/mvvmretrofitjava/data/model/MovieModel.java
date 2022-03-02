package com.android.mvvmretrofitjava.data.model;

import com.google.gson.annotations.SerializedName;

public class MovieModel {
    @SerializedName("id")
    private int id;
    @SerializedName("author")
    private String title;
    @SerializedName("download_url")
    private String image;

    public MovieModel(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getImage() {
        return image;
    }






}
