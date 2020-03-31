package com.valentine.dao;

import com.valentine.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {

    @Cacheable(value = "user", key = "#username")
    User findByUsername(String username);

}
