package com.zuhlke.idam.infrastructure.persistence;

import com.zuhlke.idam.domain.UserRepository;

public class MockUserRepository implements UserRepository {

    @Override
    public String nextId() {
        return "1";
    }
}
