package com.example.trafficnewsandrules.Interfaces;

import com.example.trafficnewsandrules.Models.Event;
import com.example.trafficnewsandrules.Models.ImageUploads;
import com.example.trafficnewsandrules.Models.News;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface NewsInterface {
    @POST("news")
    Call<Void> addNews(@Header("Cookie") String cookie, @Body News news);


    // 2. Using @Field
    @FormUrlEncoded
    @POST("news")
    Call<Void> addNews(@Header("Cookie") String cookie,
                        @Field("title") String title,
                        @Field("desc") String desc);

    // 3. Using @FieldMap
    @FormUrlEncoded
    @POST("news")
    Call<Void> addNews(@Header("Cookie") String cookie,@FieldMap Map<String,String> map);

    // For uploading image
    @Multipart
    @POST("upload")
    Call<ImageUploads> uploadImage(@Header("Cookie") String cookie, @Part MultipartBody.Part img );

    @GET("news")
    Call<List<News>> getNews(@Header("Cookie") String cookie);

    @GET("news/{id}")
    Call<News> getNewsDetail(@Header("Cookie") String cookie , @Path("id") String id);
}
