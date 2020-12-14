package com.zuhlke.authenticator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OTPGeneratorTest {

    @Test
    public void OTPGenerator_generateSecretSucceed() {

        OTPGenerator otpGenerator = new OTPGenerator();
        Assertions.assertEquals(20, otpGenerator.generateSecretKey().length());
    }

    @Test
    public void OTPGenerator_generateQRCodeSucceed() {
        String secretKey = "zuhlkeempoweringidea";
        User user = new User("testuser1", "testuser1@example.com", "test123");
        OTPGenerator otpGenerator = new OTPGenerator();

        String qrCodeUri = otpGenerator.generateQRCode(user, secretKey);
        Assertions.assertTrue(qrCodeUri.contains("otpauth://totp/Example%3Atestuser1%40example%2Ecom%3Fsecret%3DPJ2WQ3DLMVSW24DPO5SXE2LOM5UWIZLB"));
    }

    @Test
    public void OTPGenerator_generateTOTPSucceed() {

        long unixTime = (System.currentTimeMillis() / 1000L);
        long timeAsCounter = unixTime / 30;
        OTPGenerator otpGenerator = new OTPGenerator();
        String otp = otpGenerator.generateTOTP(otpGenerator.generateSecretKey(), timeAsCounter);
        Assertions.assertEquals(6, otp.length());
    }
}
