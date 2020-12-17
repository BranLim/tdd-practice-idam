package com.zuhlke.idam.application;

import com.zuhlke.idam.application.command.RegisterUserCommand;
import com.zuhlke.idam.domain.AuthenticatedUser;
import com.zuhlke.idam.domain.UserRepository;
import com.zuhlke.idam.infrastructure.persistence.MockUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IdentityApplicationServiceTest {


    @Test
    public void registerUserAndSaveToRepositorySuccess() {

        String username = "testuser1";
        String password = "password";
        String email = "testuser1@example.com";

        UserRepository userRepository = new MockUserRepository();
        IdentityApplicationService applicationService = new IdentityApplicationService(userRepository);

        RegisterUserCommand registerUserCommand = new RegisterUserCommand(username, password, email);
        String newUserId = applicationService.registerUser(registerUserCommand);
        Assertions.assertEquals("1", newUserId);
    }

    @Test
    public void registeredUserLoginSuccess() {

        String username = "testuser1";
        String password = "password";

        UserRepository userRepository = new MockUserRepository();

        IdentityApplicationService applicationService = new IdentityApplicationService(userRepository);
        AuthenticatedUser authenticatedUser = applicationService.login(username, password);

        Assertions.assertNotNull(authenticatedUser);
        Assertions.assertEquals(username,authenticatedUser.getUsername());
    }
}
