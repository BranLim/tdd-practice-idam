package com.layhilltech.idam.application;

import com.layhilltech.idam.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ApplicationServiceTest {

    protected User testUser() {
        String username = "testuser1";
        char[] password = {'p','a','s','s','w','o','r','d','1','2','3'};
        String email = "testuser1@example.com";

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B);
        return new User("1", username, email, bCryptPasswordEncoder.encode(new String(password)));
    }
}
