package com.valentine.dao;

import com.valentine.model.Comment;
import org.springframework.data.repository.CrudRepository;


public interface CommentDao extends CrudRepository<Comment, Integer> {
    /**
     * @param id
     * @return
     */
    Comment getByCommentById(Integer id);

    /**
     * @param comment
     * @return
     */
    Comment addComment(Comment comment);

    /**
     *
     * @param id
     */
    void deleteCommentById(Integer id);

}
