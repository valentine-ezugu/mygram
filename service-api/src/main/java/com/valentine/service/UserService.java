package com.valentine.service;


import com.valentine.model.User;
import com.valentine.model.security.UserRequest;

import java.util.List;
import java.util.Optional;


public interface UserService {

    void resetCredentials();

    Optional<User> findById(Integer id);

    User findByUsername(String username);

    List<User> findAll();

    User register(UserRequest user);

    void followAUser(User user);
}
