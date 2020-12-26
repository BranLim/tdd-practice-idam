package com.layhilltech.idam.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class PasswordService {

    public boolean isShort(String password) {
        return password.length() < 8;
    }

    public String generateSecretKeyForTotp() {
        SecureRandom secureRandom = new SecureRandom();
        int leftLimit = 48;
        int rightLimit = 122;
        String secretKey = secureRandom.ints(leftLimit, rightLimit + 1)
                .limit(20)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return secretKey;
    }

    public String protect(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = EncryptionFactory.getBcryptEncryption();
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean match(String userPassword, String encryptedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = EncryptionFactory.getBcryptEncryption();
        return bCryptPasswordEncoder.matches(userPassword,encryptedPassword);
    }
}
