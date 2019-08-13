package com.example.trafficnewsandrules.Interfaces;

import com.example.trafficnewsandrules.Models.LoginSignupResponse;
import com.example.trafficnewsandrules.Models.RegisterResponse;
import com.example.trafficnewsandrules.Models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserInterface {
//    @FormUrlEncoded
//    @POST("users/signup")
//    Call<LoginSignupResponse> registerUser(@Field("id") String id,
//                                        @Field("name") String name,
//                                        @Field("email") String email,
//                                        @Field("name") String username,
//                                        @Field("contact") String contact,
//                                        @Field("citizenship") String citizenship,
//                                        @Field("password") String password
//                                        );
@POST("users/signup")
Call<RegisterResponse> registerUser(@Body User user);


    @FormUrlEncoded
    @POST("users/login")
    Call<LoginSignupResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @GET("/users/{id}")
    Call<User> getUserProfile(@Path("id") String id);

    @GET("users/logout")
    Call<Void> logout(@Header("Cookie") String cookie);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") String id, @Body User user);

}