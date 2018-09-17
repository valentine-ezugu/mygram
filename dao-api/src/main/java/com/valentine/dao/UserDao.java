package com.valentine.dao;

import com.valentine.model.Likes;
import com.valentine.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.jws.soap.SOAPBinding;
import java.util.List;


public interface UserDao extends CrudRepository<User, Integer> {

    List<User> findUsersByFirst_nameOrLast_name(String name);

}
