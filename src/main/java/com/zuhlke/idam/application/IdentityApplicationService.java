package com.zuhlke.idam.application;

import com.zuhlke.idam.application.command.RegisterUserCommand;
import com.zuhlke.idam.application.command.SetupMFACommand;
import com.zuhlke.idam.domain.*;
import com.zuhlke.idam.infrastructure.services.OTPGenerator;

public class IdentityApplicationService {

    private final UserRepository userRepository;

    public IdentityApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticatedUser login(String username, String password) {
        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        return authenticationService.authenticate(username, password);
    }

    public String registerUser(RegisterUserCommand registerUserCommand) {

        UserService userService = new UserService(userRepository);
        User createdUser = userService.registerUser(registerUserCommand.getUsername(), registerUserCommand.getEmail(), registerUserCommand.getPassword());
        userRepository.add(createdUser);

        return createdUser.getId();

    }

    public MFAResult setupMFA(SetupMFACommand setupMfaCommand) {
        User user = userRepository.findById(setupMfaCommand.getUserId());
        PasswordService passwordService = new PasswordService();
        OTPGenerator otpGenerator = new OTPGenerator();
        MFAResult result = user.setupMFA("idam",passwordService, otpGenerator);
        return result;
    }
}
