package com.layhilltech.idam.application;

import com.layhilltech.idam.application.command.RegisterUserCommand;
import com.layhilltech.idam.domain.*;
import com.layhilltech.idam.application.command.SetupMFACommand;
import com.layhilltech.idam.infrastructure.services.MFAService;

public class IdentityApplicationService {

    private final UserRepository userRepository;

    public IdentityApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticationResult login(String username, String password) {
        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        AuthenticationResult authenticationResult = authenticationService.authenticate(username, password);

        return authenticationResult;
    }

    public String registerUser(RegisterUserCommand registerUserCommand) {

        UserService userService = new UserService(userRepository);
        User createdUser = userService.registerUser(registerUserCommand.getUsername(), registerUserCommand.getEmail(), new String(registerUserCommand.getPassword()));
        userRepository.add(createdUser);

        return createdUser.getId();

    }

    public MFAResult setupMFA(SetupMFACommand setupMfaCommand) {
        User user = userRepository.findById(setupMfaCommand.getUserId());
        PasswordService passwordService = new PasswordService();
        MFAService MFAService = new MFAService();
        MFAResult result = user.setupMFA("idam", passwordService, MFAService);
        return result;
    }
}
