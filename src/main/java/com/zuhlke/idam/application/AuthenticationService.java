package com.zuhlke.idam.application;

import com.zuhlke.idam.domain.AuthenticatedUser;
import com.zuhlke.idam.domain.User;
import com.zuhlke.idam.domain.UserRepository;

public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticatedUser authenticate(String username, String password) {
        User foundUser = userRepository.findByUsername(username);
        if (foundUser.getUserPassword().equals(password)) {
            return new AuthenticatedUser(foundUser.getId(), foundUser.getUserName(), foundUser.getUserEmail());
        }
        return null;
    }
}
