package com.zuhlke.authenticator.infrastructure.services;

import com.zuhlke.authenticator.domain.User;
import org.apache.commons.codec.binary.Base32;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
            return Integer.toString(otp);

        } catch (Exception err) {
            return "";
        }
    }

    public String generateTotpKeyUri(String issuer, User user, String secretKey) {
        if (issuer == null || issuer.isBlank()) {
            throw new IllegalArgumentException("issuer is missing");
        }
        try {
            Base32 base32 = new Base32();
            return "otpauth://totp/" + URLEncoder.encode(issuer, "UTF-8") + "%3A" + user.getUserEmail()
                    + "?secret=" + base32.encodeToString(secretKey.getBytes(StandardCharsets.US_ASCII))
                    + "&issuer=" + issuer
                    + "&algorithm=HmacSHA256&digits=6";
        } catch (UnsupportedEncodingException e) {
            return "";
        }

    }
}
