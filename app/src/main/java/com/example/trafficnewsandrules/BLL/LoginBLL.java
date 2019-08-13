package com.example.trafficnewsandrules.BLL;

import com.example.trafficnewsandrules.Interfaces.UserInterface;
import com.example.trafficnewsandrules.Models.LoginSignupResponse;
import com.example.trafficnewsandrules.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    private String username;
    private String password;
    boolean  isSuccess = false;

    public LoginBLL(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public boolean checkUser() {

        UserInterface userInterface = Url.getInstance().create(UserInterface.class);
        Call<LoginSignupResponse> userCall = userInterface.checkUser(username,password);

        try {
            Response<LoginSignupResponse> imageResponseResponse = userCall.execute();
            if (imageResponseResponse.body().getSuccess()) {
                Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                Url.userid = imageResponseResponse.body().getId();
                isSuccess = true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }

}

