package com.layhilltech.idam.domain;

import com.layhilltech.idam.infrastructure.persistence.MockUserRepository;
import com.layhilltech.idam.infrastructure.services.MFAService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void setupUserMFASucceed() {

        UserRepository userRepository = new MockUserRepository();

        User user = new User(userRepository.nextId(), "TestUser1", "TestUser1@domain.com", "TestPassword");
        MFAResult totpKeyUri = user.setupMFA("example", new PasswordService(), new MFAService());
        Assertions.assertTrue(totpKeyUri.getKeyUri().contains("otpauth://totp/example%3ATestUser1@domain.com?secret="));
    }

    @Test
    public void userChangeEmailSucceed() {

        UserRepository userRepository = new MockUserRepository();

        User user = new User(userRepository.nextId(), "John", "john@example.com", "testing");
        user.changeEmail("john1@example.com");
        Assertions.assertEquals("john1@example.com", user.getUserEmail());
    }
}
