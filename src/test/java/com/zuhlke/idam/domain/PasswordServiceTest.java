package com.zuhlke.idam.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordServiceTest {

    @Test
    public void passwordIsShortWhenLengthLessThan8() {
        String password ="123456";

        PasswordService passwordService = new PasswordService();
        Assertions.assertTrue(passwordService.isShort(password));
    }
}
