package com.example.titulaundry.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {
    public static String BASE_URL = "http://13.58.138.65/api_laundry/";
    public static String URL_IMG = "http://13.58.138.65/api_laundry/assets/";
    public static String profileIMG = "http://13.58.138.65/api_laundry/image_profile/";
    public static String Banner = "http://13.58.138.65/api_laundry/banner/";

    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;}
}
