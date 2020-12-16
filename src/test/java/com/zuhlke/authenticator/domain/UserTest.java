package com.zuhlke.authenticator.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void setupUser2FASucceed() {
        User user = new User("TestUser1", "TestUser1@domain.com", "TestPassword");
        String totpKeyUri = user.setup2FA("example", new UserService());
        Assertions.assertTrue(totpKeyUri.contains("otpauth://totp/example%3ATestUser1@domain.com?secret="));
    }

    @Test
    public void registerUserSucceed() {
        UserService userService = new UserService();
        User createdUser = userService.registerUser("Brandon", "brandon.lim@zuhlke", "password");
        Assertions.assertNotNull(createdUser);
    }

    @Test
    public void createUserErrorWhenUserNameNotProvided() {
        UserService userService = new UserService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("", "brandon.lim@zuhlke", "password"));
    }

    @Test
    public void createUserErrorWhenPasswordNotProvided(){
        UserService userService = new UserService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("Brandon", "brandon.lim@zuhlke", null));
    }

    @Test
    public void userChangeEmailSucceed() {
        User user = new User("John", "john@example.com", "testing");
        user.changeEmail("john1@example.com");
        Assertions.assertEquals("john1@example.com", user.getUserEmail());
    }
}
