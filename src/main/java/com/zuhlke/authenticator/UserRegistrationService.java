package com.zuhlke.authenticator;

public class UserRegistrationService {
    public User registerUser(String userName, String userEmail, String userPassword) {

        return new User(userName, userEmail, userPassword);
    }
}
