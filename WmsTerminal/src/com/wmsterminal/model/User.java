package com.wmsterminal.model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String token;
    private String device_token_id;

    public String getDevice_token_id() {
        return device_token_id;
    }

    public void setDevice_token_id(String device_token_id) {
        this.device_token_id = device_token_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
