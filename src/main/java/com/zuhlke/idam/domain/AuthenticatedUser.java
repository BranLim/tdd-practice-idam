package com.zuhlke.idam.domain;

public class AuthenticatedUser {


    private final String userEmail;
    private String username;

    public AuthenticatedUser(String username, String userEmail) {
        this.username = username;
        this.userEmail = userEmail;
    }

    public String getUsername() {
        return username;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
