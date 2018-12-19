package com.valentine.dao;

import com.valentine.model.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotosRepository extends CrudRepository<Photo,Integer>{

//    @Query("")
//    Photo uploadPhoto(String name);

    /**
     * return all the photos that belong to a single user
     * @param id
     * @return
     */
    List<Photo> findAllByUserId(Integer id);

}
