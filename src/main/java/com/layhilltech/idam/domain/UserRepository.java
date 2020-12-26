package com.layhilltech.idam.domain;

public interface UserRepository {

    String nextId();

     User findById(String userId);

     void add(User user );

    User findByUsername(String username);
}
