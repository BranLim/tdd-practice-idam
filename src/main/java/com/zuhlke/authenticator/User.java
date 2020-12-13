package com.zuhlke.authenticator;

public class User {
    private final String userPassword;
    private final String userEmail;
    private final String userName;

    public User(String userName, String userEmail, String userPassword) {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("missing username");
        }
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }


    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }
}
