package com.valentine.dao;

import com.valentine.model.Comment;
import org.springframework.data.repository.CrudRepository;


public interface CommentDao extends CrudRepository<Comment, Integer> {

}
