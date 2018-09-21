package com.valentine.dao;

import com.valentine.model.Likes;
import com.valentine.model.Photo;
import com.valentine.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface LikesDao  extends CrudRepository<Likes, Integer> {


    Collection<Likes> findByUserIdAndPhotoId(Integer UserId, Integer photoId);


}
