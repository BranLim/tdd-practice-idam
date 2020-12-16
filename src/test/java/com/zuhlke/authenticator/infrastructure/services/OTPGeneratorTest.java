package com.zuhlke.authenticator.infrastructure.services;

import com.zuhlke.authenticator.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OTPGeneratorTest {

    @Test
    public void generateSecretOf20CharactersSucceed() {

        OTPGenerator otpGenerator = new OTPGenerator();
        Assertions.assertEquals(20, otpGenerator.generateSecretKey().length());
    }

    @Test
    public void generateTotpKeyUriSucceed() {
        String secretKey = "zuhlkeempoweringidea";
        User user = new User("testuser1", "testuser1@example.com", "test123");
        OTPGenerator otpGenerator = new OTPGenerator();

        String totpKeyUri = otpGenerator.generateTotpKeyUri("example", user, secretKey);
        Assertions.assertTrue(totpKeyUri.contains("otpauth://totp/example%3Atestuser1@example.com?secret=PJ2WQ3DLMVSW24DPO5SXE2LOM5UWIZLB"));
    }

    @Test
    public void errorWhenGenerateTotpKeyWithoutIssuer() {
        String secretKey = "zuhlkeempoweringidea";
        User user = new User("testuser1", "testuser1@example.com", "test123");
        OTPGenerator otpGenerator = new OTPGenerator();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            otpGenerator.generateTotpKeyUri(null, user, secretKey);
        });
    }

    @Test
    public void generateSixDigitTOTPSucceed() {

        long unixTime = (System.currentTimeMillis() / 1000L);
        long timeAsCounter = unixTime / 30;
        OTPGenerator otpGenerator = new OTPGenerator();
        String otp = otpGenerator.generateTOTP(otpGenerator.generateSecretKey(), timeAsCounter);
        Assertions.assertEquals(6, otp.length());
    }
}
