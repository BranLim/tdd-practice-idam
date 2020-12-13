package com.zuhlke.authenticator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRegistrationTest {

    @Test
    public void UserRegistration_createUserSucceed() {
        UserRegistrationService registrationService = new UserRegistrationService();
        User createdUser = registrationService.registerUser("Brandon", "brandon.lim@zuhlke", "password");
        Assertions.assertNotNull(createdUser);
    }

    @Test
    public void UserRegistration_createUserError_WhenUserNameNotProvided() {
        UserRegistrationService registrationService = new UserRegistrationService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> registrationService.registerUser("", "brandon.lim@zuhlke", "password"));
    }
}
