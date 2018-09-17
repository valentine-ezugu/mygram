package com.valentine.dao;

import com.valentine.model.Likes;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface LikesDao  extends CrudRepository<Likes, Integer> {

    //getAllLIkes where photoId = ?

    Collection<Likes> findAllByPhoto_id(Integer id);
 }
