package com.zuhlke.idam.domain;

import com.zuhlke.idam.infrastructure.services.OTPGenerator;

public class User {

    private String userPassword;
    private String userEmail;
    private String userName;
    private String id;
    private String secretKey;

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

    public MFAResult setupMFA(String issuer, PasswordService passwordService, OTPGenerator otpGenerator) {

        secretKey = passwordService.generateSecretKeyForTotp();
        String mfaQrUri =  otpGenerator.generateTotpKeyUri(issuer, this, secretKey);

        MFAResult setupResult = new MFAResult(mfaQrUri, secretKey);
        return setupResult;
    }

    public void changeEmail(String newEmail) {
        userEmail = newEmail;
    }

    public String getId() {
        return id;
    }
}
