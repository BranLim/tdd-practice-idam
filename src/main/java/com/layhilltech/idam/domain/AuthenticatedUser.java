package com.layhilltech.idam.domain;

public class AuthenticatedUser {


    private final String userEmail;
    private String username;
    private String id;

    public AuthenticatedUser(String id, String username, String userEmail) {
        this.id = id;
        this.username = username;
        this.userEmail = userEmail;
    }

    public String getUsername() {
        return username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getId() {
        return id;
    }
}
