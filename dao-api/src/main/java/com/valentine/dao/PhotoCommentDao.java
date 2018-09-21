package com.valentine.dao;

import com.valentine.model.PhotoComment;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface PhotoCommentDao extends CrudRepository<PhotoComment, Integer> {

    List<PhotoComment> findAll();
 }
