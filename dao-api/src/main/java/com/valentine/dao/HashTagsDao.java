package com.valentine.dao;

import com.valentine.model.HashTag;
import org.springframework.data.repository.CrudRepository;

public interface HashTagsDao  extends CrudRepository<HashTag, Integer> {

    HashTag findByHashtagname(String name);
}
