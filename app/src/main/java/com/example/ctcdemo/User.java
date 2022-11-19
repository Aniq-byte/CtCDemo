package com.example.ctcdemo;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String email;
    public String password;

    public User(String email, String password) {

        this.email = email;
        this.password = password;


    }

}
