package com.zuhlke.idam.infrastructure.services;

import com.zuhlke.idam.domain.PasswordService;
import com.zuhlke.idam.domain.User;
import com.zuhlke.idam.domain.UserRepository;
import com.zuhlke.idam.infrastructure.persistence.MockUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MFAServiceTest {



    @Test
    public void successWhenTotpkeyUriIsGenerated() {
        String secretKey = "zuhlkeempoweringidea";

        UserRepository userRepository = new MockUserRepository();
        User user = new User(userRepository.nextId(), "testuser1", "testuser1@example.com", "test123");
        MFAService MFAService = new MFAService();

        String totpKeyUri = MFAService.generateTotpKeyUri("example", user, secretKey);
        Assertions.assertTrue(totpKeyUri.contains("otpauth://totp/example%3Atestuser1@example.com?secret=PJ2WQ3DLMVSW24DPO5SXE2LOM5UWIZLB"));
    }

    @Test
    public void errorWhenGenerateTotpKeyWithoutIssuer() {
        String secretKey = "zuhlkeempoweringidea";

        UserRepository userRepository = new MockUserRepository();

        User user = new User(userRepository.nextId(), "testuser1", "testuser1@example.com", "test123");
        MFAService MFAService = new MFAService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MFAService.generateTotpKeyUri(null, user, secretKey);
        });
    }

    @Test
    public void generateSixDigitTOTPSucceed() {

        long unixTime = (System.currentTimeMillis() / 1000L);
        long timeAsCounter = unixTime / 30;
        PasswordService passwordService = new PasswordService();
        MFAService MFAService = new MFAService();
        String otp = MFAService.generateTOTP(passwordService.generateSecretKeyForTotp(), timeAsCounter);
        Assertions.assertEquals(6, otp.length());
    }
}
