package com.example.trafficnewsandrules.Models;

public class LoginSignupResponse {
    public boolean success;
    private String status;
    private String user;

    public boolean getSuccess() {
        return success;
    }

    public String getId(){
        return user;
    }

    public void setId(String user){
        this.user = user;
    }

    public String getStatus(){
        return status;
    }
}
