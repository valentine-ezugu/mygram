package com.valentine.service;

import com.valentine.dao.AuthorityRepository;
import com.valentine.model.security.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServiceImpl  implements AuthorityService {


    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> findById(Long id) {
        Optional<Authority> auth = this.authorityRepository.findById(id);
        List<Authority> auths = new ArrayList<>();
        if(auth.isPresent()){
            Authority authority = auth.get();
            auths.add(authority);
        }
        return auths;
    }

    @Override
    public List<Authority> findByName(String name) {
        Authority auth = this.authorityRepository.findByName(name);
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }


}
