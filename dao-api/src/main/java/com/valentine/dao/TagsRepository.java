package com.valentine.dao;

import com.valentine.model.Tags;
import org.springframework.data.repository.CrudRepository;

public interface TagsRepository extends CrudRepository<Tags, Integer> {
}
