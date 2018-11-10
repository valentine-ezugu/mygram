package com.valentine.dao;

import com.valentine.model.security.Authority;
import org.springframework.data.repository.CrudRepository;


public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority findByName(String name);

}
