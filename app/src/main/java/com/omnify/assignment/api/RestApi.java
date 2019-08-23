package com.omnify.assignment.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by neeraj on 9/20/2018.
 */


public class RestApi {

    public static final String BASE_URL = "https://hacker-news.firebaseio.com/v0/";

    public static ApiInterface getApiInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }

}
