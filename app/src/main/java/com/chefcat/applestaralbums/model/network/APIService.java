package com.chefcat.applestaralbums.model.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    public static AppleAlbumInterface appleAlbums(){
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AppleAlbumInterface api = adapter.create(AppleAlbumInterface.class);
        return api;
    }
}
