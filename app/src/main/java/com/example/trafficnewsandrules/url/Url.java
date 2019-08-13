package com.example.trafficnewsandrules.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
    public static final String BASE_URL = "http://10.0.2.2:3000/";
    //public static final String BASE_URL = "http://192.168.123.8:3000/";

    public static String Cookie;

    public static String userid="";
    //Create an instance of Retrofit
    public static Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
