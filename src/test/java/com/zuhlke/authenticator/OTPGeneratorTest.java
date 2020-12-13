package com.zuhlke.authenticator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class OTPGeneratorTest {

    @Test
    public void OTPGenerator_generateSecretSucceed() {

        OTPGenerator otpGenerator = new OTPGenerator();
        Assertions.assertEquals(20, otpGenerator.generateSecretKey().length());
    }

    @Test
    public void OTPGenerator_generateQRCodeSucceed() {
        OTPGenerator otpGenerator = new OTPGenerator();
        String secretKey = otpGenerator.generateSecretKey();
        String qrCodeUri = otpGenerator.generateQRCode(secretKey);
        Assertions.assertTrue(qrCodeUri.contains("otpauth://totp/"));

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
