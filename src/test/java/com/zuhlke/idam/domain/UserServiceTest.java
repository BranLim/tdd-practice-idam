package com.zuhlke.idam.domain;

import com.zuhlke.idam.infrastructure.persistence.MockUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    public void registerUserAndAddUserToRepositorySuccess() {

        UserRepository userRepository = new MockUserRepository();

        UserService userService = new UserService(userRepository);
        User createdUser = userService.registerUser("Brandon", "brandon.lim@zuhlke", "password");
        Assertions.assertNotNull(createdUser);
        Assertions.assertEquals("1", createdUser.getId());
    }

    @Test
    public void registerUserErrorWhenUserNameNotProvided() {
        UserRepository userRepository = new MockUserRepository();

        UserService userService = new UserService(userRepository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("", "brandon.lim@zuhlke", "password"));
    }

    @Test
    public void registerUserErrorWhenPasswordNotProvided() {

        UserRepository userRepository = new MockUserRepository();

        UserService userService = new UserService(userRepository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("Brandon", "brandon.lim@zuhlke", null), "password is missing");
    }

    @Test
    public void registerUserErrorWhenPasswordLengthLessThan8() {
        UserRepository userRepository = new MockUserRepository();

        UserService userService = new UserService(userRepository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.registerUser("Brandon", "brandon.lim@zuhlke", "pass"), "password should be more than 7 characters");
    }
}
