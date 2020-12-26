package com.layhilltech.idam.application;

import com.layhilltech.idam.application.command.RegisterUserCommand;
import com.layhilltech.idam.application.command.SetupMFACommand;
import com.layhilltech.idam.domain.AuthenticationResult;
import com.layhilltech.idam.domain.MFAResult;
import com.layhilltech.idam.domain.User;
import com.layhilltech.idam.domain.UserRepository;
import com.layhilltech.idam.infrastructure.persistence.MockUserRepository;
import org.apache.commons.codec.binary.Base32;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class IdentityApplicationServiceTest extends ApplicationServiceTest {


    @Test
    public void successWhenUserIsRegisteredAndSavedToRepository() {

        String username = "testuser1";
        char[] password = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        String email = "testuser1@example.com";

        UserRepository userRepository = new MockUserRepository();
        IdentityApplicationService applicationService = new IdentityApplicationService(userRepository);

        RegisterUserCommand registerUserCommand = new RegisterUserCommand(username, password, email);
        String newUserId = applicationService.registerUser(registerUserCommand);
        Assertions.assertEquals("1", newUserId);
    }

    @Test
    public void successWhenRegisteredUserLoginWithValidUsernameAndPassword() {

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
    public void successWhenRegisteredUserReceivedValidToTpUriWhileSettingUpMFA() {

        User user = this.testUser();

        UserRepository userRepository = new MockUserRepository();
        userRepository.add(user);

        SetupMFACommand setup2faCommand = new SetupMFACommand(user.getId());
        IdentityApplicationService applicationService = new IdentityApplicationService(userRepository);
        MFAResult result = applicationService.setupMFA(setup2faCommand);

        Base32 base32 = new Base32();
        String validTotp = "otpauth://totp/idam%3Atestuser1@example.com"
                + "?secret=" + base32.encodeToString(result.getSecretKey().getBytes(StandardCharsets.US_ASCII))
                + "&issuer=idam&algorithm=HmacSHA256&digits=6";
        Assertions.assertNotNull(result);
        Assertions.assertEquals(validTotp, result.getKeyUri());

    }


}
