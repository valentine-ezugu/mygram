package com.valentine.dao;

import com.valentine.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotosDao extends CrudRepository<Photo,Integer>{

}
