package com.valentine.service;

import com.valentine.model.security.Authority;

import java.util.List;

public interface AuthorityService  {

    List<Authority> findById(Long id);

    List<Authority> findByName(String name);

}
