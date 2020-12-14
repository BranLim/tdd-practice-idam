package com.zuhlke.authenticator.domain;

import com.zuhlke.authenticator.infrastructure.services.OTPGenerator;

public class UserService {

    public String setup2FA(User user) {
        OTPGenerator otpGenerator = new OTPGenerator();
        String secretKey = otpGenerator.generateSecretKey();
        return otpGenerator.generateTotpKeyUri(user, secretKey);
    }

    public User registerUser(String userName, String userEmail, String userPassword) {

        return new User(userName, userEmail, userPassword);
    }
}