package com.example.trafficnewsandrules.BLL;


import com.example.trafficnewsandrules.Interfaces.UserInterface;
import com.example.trafficnewsandrules.Models.LoginSignupResponse;
import com.example.trafficnewsandrules.Models.RegisterResponse;
import com.example.trafficnewsandrules.Models.User;
import com.example.trafficnewsandrules.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterBLL {
    private String name;
    private String email;
    private String username;
    private String contact;
    private String citizenship;
    private String password;
    boolean isSuccess = false;

    public RegisterBLL(String name, String email, String username, String contact, String citizenship, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.contact = contact;
        this.citizenship = citizenship;
        this.password = password;
    }

    public RegisterBLL()
    {

    }


    public boolean registerUser(User user) {
        UserInterface userInterface = Url.getInstance().create(UserInterface.class);
        Call<RegisterResponse> userCall = userInterface.registerUser(user);

        try {
            Response<RegisterResponse> imageResponseResponse = userCall.execute();
          if(imageResponseResponse.isSuccessful())
          {
              isSuccess = true;
          }
        }catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}

