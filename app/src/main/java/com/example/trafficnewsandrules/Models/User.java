package com.example.trafficnewsandrules.Models;

public class User {

    private  String name;
    private  String email;
    private  String username;
    private  String contact;
    private  String citizenship;
    private  String password;

    public User( String name, String email,String username, String contact,String citizenship,String password) {
        //this.user = user;
        this.name = name;
        this.email = email;
        this.username = username;
        this.contact = contact;
        this.citizenship = citizenship;
        this.password = password;

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                ",name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", contact='" + contact + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

