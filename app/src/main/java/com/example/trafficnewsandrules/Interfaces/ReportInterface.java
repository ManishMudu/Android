package com.example.trafficnewsandrules.Interfaces;

import com.example.trafficnewsandrules.Models.ImageUploads;
import com.example.trafficnewsandrules.Models.Report;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ReportInterface {

    @POST("reports")
    Call<Void> addReport(@Body Report report);

//    @FormUrlEncoded
//    @POST("events")
//    Call<Void> addReport(@Header("Cookie") String cookie, @FieldMap Map<String,String> map);

    @Multipart
    @POST("upload")
    Call<ImageUploads> uploadImage( @Part MultipartBody.Part img );

}
