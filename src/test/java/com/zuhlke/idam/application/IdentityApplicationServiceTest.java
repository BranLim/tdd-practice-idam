package com.zuhlke.idam.application;

import com.zuhlke.idam.application.command.RegisterUserCommand;
import com.zuhlke.idam.application.command.SetupMFACommand;
import com.zuhlke.idam.domain.AuthenticationResult;
import com.zuhlke.idam.domain.MFAResult;
import com.zuhlke.idam.domain.User;
import com.zuhlke.idam.domain.UserRepository;
import com.zuhlke.idam.infrastructure.persistence.MockUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IdentityApplicationServiceTest extends ApplicationServiceTest {


    @Test
    public void registerUserAndSaveToRepositorySuccess() {

        String username = "testuser1";
        char[] password = {'p','a','s','s','w','o','r','d'};
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
        String password = "password123";

        User user = this.testUser();

        UserRepository userRepository = new MockUserRepository();
        userRepository.add(user);
        IdentityApplicationService applicationService = new IdentityApplicationService(userRepository);

        AuthenticationResult authenticationResult = applicationService.login(username, password);

        Assertions.assertNotNull(authenticationResult);
        Assertions.assertEquals("1", authenticationResult.getAuthenticatedUser().getId());
        Assertions.assertEquals(username, authenticationResult.getAuthenticatedUser().getUsername());
    }

    @Test
    public void errorWhenLoginWithNonexistentUserName() {

        String username = "testuser2";
        String password = "password";

        User user = this.testUser();

        UserRepository userRepository = new MockUserRepository();
        userRepository.add(user);
        IdentityApplicationService applicationService = new IdentityApplicationService(userRepository);
        AuthenticationResult authenticationResult = applicationService.login(username, password);

        Assertions.assertTrue(authenticationResult.failed());
        Assertions.assertEquals("no such user", authenticationResult.getMessage());
    }

    @Test
    public void errorWhenLoginWithIncorrectPassword() {
        String username = "testuser1";
        String password = "password";

        User user = this.testUser();

        UserRepository userRepository = new MockUserRepository();
        userRepository.add(user);
        IdentityApplicationService applicationService = new IdentityApplicationService(userRepository);
        AuthenticationResult authenticationResult = applicationService.login(username, password);

        Assertions.assertTrue(authenticationResult.failed());
        Assertions.assertEquals("invalid password", authenticationResult.getMessage());
    }

    @Test
    public void registeredUserSetupMFASuccess() {

        User user = this.testUser();

        UserRepository userRepository = new MockUserRepository();
        userRepository.add(user);

        SetupMFACommand setup2faCommand = new SetupMFACommand(user.getId());
        IdentityApplicationService applicationService = new IdentityApplicationService(userRepository);
        MFAResult result = applicationService.setupMFA(setup2faCommand);

        Assertions.assertNotNull(result);

    }

}
