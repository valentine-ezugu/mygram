package com.valentine.dao;

import com.valentine.messenger.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupDao extends CrudRepository<Group,Integer> {
}
