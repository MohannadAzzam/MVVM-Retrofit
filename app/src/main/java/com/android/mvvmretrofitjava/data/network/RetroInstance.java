package com.android.mvvmretrofitjava.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {


    public static String BASE_URL = " https://picsum.photos/";             /*https://picsum.photos/v2/list*/

    private static Retrofit retrofit;

    public static Retrofit getRetroClient() {
        if(retrofit == null ) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
