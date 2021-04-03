package com.valentine.service;

import com.valentine.dao.UserRepository;
import com.valentine.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          return Optional.ofNullable(userRepository.findByUsername(username)).orElseThrow(()
              -> new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));
    }

    public void changePassword(String oldPassword, String newPassword) {
    if (authenticationManager == null ) {
       LOGGER.debug("No authentication manager set. can't change Password!");
       return;
     }
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        LOGGER.debug("Re-authenticating user '"+ username + "' for password change request.");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        User user = (User) loadUserByUsername(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

 }
