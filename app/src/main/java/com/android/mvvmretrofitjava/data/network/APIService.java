package com.android.mvvmretrofitjava.data.network;

import com.android.mvvmretrofitjava.data.model.Details;
import com.android.mvvmretrofitjava.data.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {


    @GET("v2/list?page=1&limit=100")
    Call<List<MovieModel>> getMovieList();

    @GET("id/{id}/info")
    Call<Details> getMovieListById(@Path("id") int id );
}
