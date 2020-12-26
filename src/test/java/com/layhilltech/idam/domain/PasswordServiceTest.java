package com.layhilltech.idam.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordServiceTest {

    @Test
    public void passwordIsShortWhenLengthLessThan8() {
        char[] password ={'1','2','3','4','5','6'};

        PasswordService passwordService = new PasswordService();
        Assertions.assertTrue(passwordService.isShort(new String(password)));
    }

    @Test
    public void generateSecretOf20CharactersForTotpSucceed() {

        PasswordService passwordService = new PasswordService();
        Assertions.assertEquals(20, passwordService.generateSecretKeyForTotp().length());
    }
}
