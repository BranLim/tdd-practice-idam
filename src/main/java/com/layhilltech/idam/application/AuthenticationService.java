package com.layhilltech.idam.application;

import com.layhilltech.idam.domain.*;

public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticationResult authenticate(String username, String password) {
        User foundUser = userRepository.findByUsername(username);
        if (foundUser == null) {
            return new AuthenticationResult(true, "no such user");
        }
        PasswordService passwordService = new PasswordService();

        if (passwordService.match(password,foundUser.getUserPassword())) {
            AuthenticatedUser authenticatedUser = new AuthenticatedUser(foundUser.getId(), foundUser.getUserName(), foundUser.getUserEmail());
            AuthenticationResult authenticationResult = new AuthenticationResult(authenticatedUser);
            return authenticationResult;
        } else {
            return new AuthenticationResult(true, "invalid password");
        }

    }
}
