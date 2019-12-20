package com.valentine.gram.controller;


import com.valentine.model.User;
import com.valentine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping(value = "/api")
public class FollowController {

    @Autowired
    private UserService userService;

    @PostMapping("/follow/{id}")
    void addFollowerById(@PathVariable Integer id) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null) {
            Optional<User> userId = userService.findById(id);
            if (userId.isPresent()) {
                User user = userId.get();
                userService.followAUser(user);
            }
        }

    }

    @GetMapping("/followers/{currentUser}")
    void getAllFollowers(User currentUser) {

    }


}
