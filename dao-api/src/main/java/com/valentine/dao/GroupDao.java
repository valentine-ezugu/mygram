package com.valentine.dao;


import com.valentine.messenger.Groups;
import org.springframework.data.repository.CrudRepository;

public interface GroupDao extends CrudRepository<Groups,Integer> {
}
