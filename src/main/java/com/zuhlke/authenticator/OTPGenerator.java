package com.zuhlke.authenticator;

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

    public String generateQRCode(String secretKey) {
        try{
            Base32 base32 = new Base32();
            return "otpauth://totp/" + URLEncoder.encode("Example:brandon.lim@zuhlke.com?secret=", "UTF-8")
                    + base32.encodeToString(secretKey.getBytes(StandardCharsets.US_ASCII))
                    + URLEncoder.encode("&algorithm=HmacSHA256&digits=6", "UTF-8");
        }catch(UnsupportedEncodingException e){
            return "";
        }
    }
}
