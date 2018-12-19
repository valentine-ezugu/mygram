package com.valentine.service;

import com.valentine.dao.UserRepository;
import com.valentine.model.User;
import com.valentine.model.security.Authority;
import com.valentine.model.security.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authService;

    @Override
    public void resetCredentials() {
        List<User> users = (List<User>) userRepository.findAll();
        for (User user : users) {
            user.setPassword(passwordEncoder.encode("123"));
            userRepository.save(user);
        }
    }


    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User save(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setFirstName(userRequest.getFirstname());
        user.setLastName(userRequest.getLastname());

        List<Authority> auth = authService.findByname("ROLE_USER");
        user.setAuthorities(auth);
        userRepository.save(user);
        return user;
    }

    @Override
    public void followAUser(User user) {
        user.getFollowing().add(user);
        userRepository.save(user);
    }
}
