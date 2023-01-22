package com.tarik.tvmaze.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    public static final String BASE_URL = "https://api.tvmaze.com/";
    public static Retrofit instance = null;

    public static Retrofit getRetrofit() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

}
