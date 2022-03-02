package com.android.mvvmretrofitjava.data.model;

import com.google.gson.annotations.SerializedName;

public class Details {
    private int id;
    private String author;
    @SerializedName("download_url")
    private String image;

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public Details() {
    }

    public String getImage() {
        return image;
    }

    public Details(int id, String author, String image) {
        this.id = id;
        this.author = author;
        this.image = image;
    }
}
