package com.zuhlke.idam.domain;

public class User {

    private String userPassword;
    private String userEmail;
    private String userName;
    private String id;

    public User(String id, String userName, String userEmail, String userPassword) {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("missing username");
        }
        if (userPassword == null || userPassword.isBlank()) {
            throw new IllegalArgumentException("missing password");
        }
        this.id = id;
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

    public String setup2FA(String issuer, UserService userService) {
        return userService.setup2FA(issuer, this);
    }

    public void changeEmail(String newEmail) {
        userEmail = newEmail;
    }

    public String getId() {
        return id;
    }
}
