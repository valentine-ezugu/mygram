package com.valentine.dao;

 import com.valentine.model.Photo;
 import com.valentine.model.User;
import org.springframework.data.repository.CrudRepository;

 import java.util.Collection;


public interface UserDao extends CrudRepository<User, Integer> {

       Collection<User> findByPhotoLike(Photo photo);
}
