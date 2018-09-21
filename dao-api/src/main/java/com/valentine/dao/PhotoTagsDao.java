package com.valentine.dao;

import com.valentine.model.PhotoTag;
import org.springframework.data.repository.CrudRepository;

public interface PhotoTagsDao extends CrudRepository<PhotoTag, Integer> {

}
