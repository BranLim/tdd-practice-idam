package com.zuhlke.idam.application;

import com.zuhlke.idam.application.command.RegisterUserCommand;
import com.zuhlke.idam.domain.AuthenticatedUser;
import com.zuhlke.idam.domain.User;
import com.zuhlke.idam.domain.UserRepository;
import com.zuhlke.idam.domain.UserService;

public class IdentityApplicationService {

    private final UserRepository userRepository;

    public IdentityApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticatedUser login(String username, String password) {
        return new AuthenticatedUser(username,null);
    }

    public String registerUser(RegisterUserCommand registerUserCommand) {

        UserService userService = new UserService(userRepository);
        User createdUser = userService.registerUser(registerUserCommand.getUsername(), registerUserCommand.getEmail() ,registerUserCommand.getPassword());
        return createdUser.getId();

    }
}
