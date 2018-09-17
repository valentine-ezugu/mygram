package com.valentine.dao;

import com.valentine.model.PhotoTags;
import org.springframework.data.repository.CrudRepository;

public interface PhotoTagsDao extends CrudRepository<PhotoTags, Integer> {

}
