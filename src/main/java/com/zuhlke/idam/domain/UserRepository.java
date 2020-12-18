package com.zuhlke.idam.domain;

public interface UserRepository {

    String nextId();

     User findById(String userId);

     void add(User user );
}
