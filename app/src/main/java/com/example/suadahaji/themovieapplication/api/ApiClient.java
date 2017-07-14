package com.example.suadahaji.themovieapplication.api;

import com.example.suadahaji.themovieapplication.util.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by suadahaji
 */

public class ApiClient {

    private Retrofit retrofit;

    public ApiClient() {
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder().build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public ApiManager provideApiManager() {
        return retrofit.create(ApiManager.class);
    }
}
