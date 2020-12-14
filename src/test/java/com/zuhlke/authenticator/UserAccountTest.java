package com.zuhlke.authenticator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserAccountTest {

    @Test
    public void UserAccount_setupOtpSuccess(){
        UserService userService = new UserService();
        String qrCode = userService.setupOtp(new User("TestUser1", "TestUser1@domain.com", "TestPassword"));
        Assertions.assertTrue(qrCode.contains("otpauth://totp/Example%3ATestUser1%40domain%2Ecom%3Fsecret="));
    }
}
