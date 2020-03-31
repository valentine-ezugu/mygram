package com.valentine.dao;

import com.valentine.model.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface PostRepository extends CrudRepository<Post, Integer> {


    @Modifying
    @Query("UPDATE Post p SET p.likes = p.likes+1 WHERE p.id=:id")
    void likePost(@Param("id") Long id);

     Post getById(Integer id);

    @Modifying
    @Query("UPDATE Post p SET p.likes=p.likes-1 WHERE p.id=:id")
    void unlikePost(@Param("id") Long id);
}
