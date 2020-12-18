package com.zuhlke.idam.infrastructure.persistence;

import com.zuhlke.idam.domain.User;
import com.zuhlke.idam.domain.UserRepository;

import java.util.HashMap;

public class MockUserRepository implements UserRepository {

    private HashMap<String, User> store;

    public MockUserRepository() {
        store = new HashMap<>();
    }

    @Override
    public String nextId() {
        return "1";
    }

    @Override
    public User findById(String userId) {
        return store.get(userId);
    }

    @Override
    public void add(User user) {
        store.put(user.getId(), user);
    }

    @Override
    public User findByUsername(String username) {
        return store.values().stream().filter(u->u.getUserName().equals(username)).findFirst().get();
    }
}
