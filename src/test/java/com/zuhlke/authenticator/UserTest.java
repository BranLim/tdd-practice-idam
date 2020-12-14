package com.zuhlke.authenticator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void UserAccount_setupUser2FASucceed(){
        UserService userService = new UserService();
        String totpKeyUri = userService.setup2FA(new User("TestUser1", "TestUser1@domain.com", "TestPassword"));
        Assertions.assertTrue(totpKeyUri.contains("otpauth://totp/Example%3ATestUser1%40domain%2Ecom%3Fsecret%3D"));
    }

    @Test
    public void UserRegistration_createUserSucceed() {
        UserService registrationService = new UserService();
        User createdUser = registrationService.registerUser("Brandon", "brandon.lim@zuhlke", "password");
        Assertions.assertNotNull(createdUser);
    }

    @Test
    public void UserRegistration_createUserError_WhenUserNameNotProvided() {
        UserService userService = new UserService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("", "brandon.lim@zuhlke", "password"));
    }
}
