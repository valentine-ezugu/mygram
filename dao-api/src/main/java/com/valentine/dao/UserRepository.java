package com.valentine.dao;

 import com.valentine.model.User;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User, Integer> {


    User findByUsername(String username);

 }
