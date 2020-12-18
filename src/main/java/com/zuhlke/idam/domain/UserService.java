package com.zuhlke.idam.domain;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User registerUser(String userName, String userEmail, char[] userPassword) {

        if (userPassword==null || userPassword.length==0){
            throw new IllegalArgumentException("password is missing");
        }
        PasswordService passwordService = new PasswordService();
        if (passwordService.isShort(userPassword)) {
            throw new IllegalArgumentException("password should be more than 7 characters");
        }
        User user = new User(userRepository.nextId(), userName, userEmail, userPassword);

        return user;
    }
}
