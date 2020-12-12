package com.zuhlke.authenticator;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class OTPGenerator {


    public String generateSecretKey() {

        SecureRandom secureRandom = new SecureRandom();
        int leftLimit = 48;
        int rightLimit = 122;
        String secretKey = secureRandom.ints(leftLimit, rightLimit + 1)
                .limit(20)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return secretKey;

    }

    public String generateTOTP(String generatedSecretKey, long timeAsCounter) {
        try {
            String algo = "HmacSHA256";
            Mac hmac = Mac.getInstance(algo);
            hmac.init(new SecretKeySpec(generatedSecretKey.getBytes(StandardCharsets.US_ASCII), algo));
            byte[] hash = hmac.doFinal(String.valueOf(timeAsCounter).getBytes(StandardCharsets.US_ASCII));
            int offset = hash[19] & 0xf;
            int hashMac = (hash[offset++] & 0x7f) << 24 | (hash[offset++] & 0xff << 16) | (hash[offset++] & 0xff) << 8 | (hash[offset++] & 0xff);
            int otp = (hashMac % 1000000);
            String finalOtp = Integer.toString(otp);
            return finalOtp;

        } catch (Exception err) {
        }
        return "";
    }
}
