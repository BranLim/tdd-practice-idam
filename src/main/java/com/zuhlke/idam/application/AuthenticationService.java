package com.zuhlke.idam.application;

import com.zuhlke.idam.domain.AuthenticatedUser;
import com.zuhlke.idam.domain.AuthenticationResult;
import com.zuhlke.idam.domain.User;
import com.zuhlke.idam.domain.UserRepository;

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
        if (foundUser.getUserPassword().equals(password)) {
            AuthenticatedUser authenticatedUser = new AuthenticatedUser(foundUser.getId(), foundUser.getUserName(), foundUser.getUserEmail());
            AuthenticationResult authenticationResult = new AuthenticationResult(authenticatedUser);
            return authenticationResult;
        }
        return null;
    }
}
