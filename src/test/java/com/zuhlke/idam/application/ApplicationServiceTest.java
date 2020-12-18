package com.zuhlke.idam.application;

import com.zuhlke.idam.domain.User;

public class ApplicationServiceTest {

    protected User testUser() {
        String username = "testuser1";
        String password = "password123";
        String email = "testuser1@example.com";
        return new User("1", username, email, password);
    }
}
