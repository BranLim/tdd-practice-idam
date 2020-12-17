package com.zuhlke.idam.domain;

import com.zuhlke.idam.infrastructure.persistence.MockUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void setupUser2FASucceed() {

        UserRepository userRepository = new MockUserRepository();

        User user = new User(userRepository.nextId(), "TestUser1", "TestUser1@domain.com", "TestPassword");
        String totpKeyUri = user.setup2FA("example", new UserService(userRepository));
        Assertions.assertTrue(totpKeyUri.contains("otpauth://totp/example%3ATestUser1@domain.com?secret="));
    }

    @Test
    public void userChangeEmailSucceed() {

        UserRepository userRepository = new MockUserRepository();

        User user = new User(userRepository.nextId(), "John", "john@example.com", "testing");
        user.changeEmail("john1@example.com");
        Assertions.assertEquals("john1@example.com", user.getUserEmail());
    }
}
