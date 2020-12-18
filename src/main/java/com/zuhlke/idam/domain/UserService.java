package com.zuhlke.idam.domain;

import com.zuhlke.idam.infrastructure.services.OTPGenerator;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String setup2FA(String issuer, User user) {
        OTPGenerator otpGenerator = new OTPGenerator();
        String secretKey = otpGenerator.generateSecretKey();
        return otpGenerator.generateTotpKeyUri(issuer, user, secretKey);
    }

    public User registerUser(String userName, String userEmail, String userPassword) {

        PasswordService passwordService = new PasswordService();
        if (passwordService.isShort(userPassword)) {
            throw new IllegalArgumentException("password should be more than 7 characters");
        }
        User user = new User(userRepository.nextId(), userName, userEmail, userPassword);

        return user;
    }
}
