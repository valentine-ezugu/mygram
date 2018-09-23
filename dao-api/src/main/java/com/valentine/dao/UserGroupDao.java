package com.valentine.dao;

import com.valentine.messenger.UserGroup;
import org.springframework.data.repository.CrudRepository;

public interface UserGroupDao  extends CrudRepository<UserGroup, Integer> {
}
