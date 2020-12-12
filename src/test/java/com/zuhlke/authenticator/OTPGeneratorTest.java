package com.zuhlke.authenticator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OTPGeneratorTest {

    @Test
    public void OTPGenerator_generateSecret(){

        OTPGenerator otpGenerator = new OTPGenerator();
        Assertions.assertEquals(20, otpGenerator.generateSecretKey().length());
    }

    @Test
    public void OTPGenerator_generateHOTP() {

        long unixTime = (System.currentTimeMillis() / 1000L);
        long timeAsCounter = unixTime / 30;
        OTPGenerator otpGenerator = new OTPGenerator();
        String otp = otpGenerator.generateTOTP(otpGenerator.generateSecretKey(), timeAsCounter);
        Assertions.assertEquals(6,otp.length());
    }
}
