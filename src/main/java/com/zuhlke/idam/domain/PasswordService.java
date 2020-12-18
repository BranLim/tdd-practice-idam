package com.zuhlke.idam.domain;

public class PasswordService {

    public boolean isShort(String password) {
        return password.length() < 8;
    }
}
