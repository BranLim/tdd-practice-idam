package com.zuhlke.idam.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionFactory {
    public static BCryptPasswordEncoder getBcryptEncryption() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B);
    }


}
