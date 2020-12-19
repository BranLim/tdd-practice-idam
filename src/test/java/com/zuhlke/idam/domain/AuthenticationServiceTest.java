package com.zuhlke.idam.domain;

import com.zuhlke.idam.application.AuthenticationService;
import com.zuhlke.idam.infrastructure.persistence.MockUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthenticationServiceTest {

    @Test
    public void authenticationSuccessWhenEnteredPasswordMatchStoredPassword() {

        PasswordService passwordService = new PasswordService();
        String encrypted = passwordService.protect("tester123");

        UserRepository userRepository = new MockUserRepository();
        userRepository.add(new User("1", "testuser2", "testuser2@example.com", encrypted));

        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        AuthenticationResult authenticationResult = authenticationService.authenticate("testuser2","tester123");

        Assertions.assertNotNull(authenticationResult);
        Assertions.assertFalse(authenticationResult.failed());
    }

    @Test
    public void authenticationFailedWhenEnteredPasswordDontMatchStoredPassword() {
        PasswordService passwordService = new PasswordService();
        String encrypted = passwordService.protect("tester123");

        UserRepository userRepository = new MockUserRepository();
        userRepository.add(new User("1", "testuser2", "testuser2@example.com", encrypted));

        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        AuthenticationResult authenticationResult = authenticationService.authenticate("testuser2","tester768");

        Assertions.assertNotNull(authenticationResult);
        Assertions.assertTrue(authenticationResult.failed(), "authentication failed");
    }
}
