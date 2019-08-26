package com.chefcat.applestaralbums.model.network;

import com.chefcat.applestaralbums.model.data.response.GetAlbumResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppleAlbumInterface {
    @GET("search")
    Call<GetAlbumResponse> getAlbumsBy(@Query("term") String term,
                                       @Query("country") String country,
                                       @Query("media") String media,
                                       @Query("attributeType") String attribute,
                                       @Query("limit") String limit);
}
