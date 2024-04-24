package com.example.csit228f2_2;

import java.net.MalformedURLException;

public class User {
    String username;
    String email;
    String password;
    String css;

    public User(String username, String email, String password) throws MalformedURLException {
        this.username = username;
        this.email = email;
        this.password = password;
        css = username + ".css";
    }
}
