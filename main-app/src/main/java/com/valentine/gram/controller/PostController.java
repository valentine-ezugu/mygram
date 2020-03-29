package com.valentine.gram.controller;

import com.valentine.model.Post;
import com.valentine.model.User;
import com.valentine.service.AwsFileStorage;
import com.valentine.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@CrossOrigin
@RequestMapping(value = "/api/post")
@RestController
public class PostController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private PostService postService;

    @Autowired
    AwsFileStorage awsFileStorage;


    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public Post upload(@RequestParam("file") MultipartFile file, String text) throws IOException {
        LOGGER.debug("upload({})", file);
        return postService.savePost(file, text);
    }

    @DeleteMapping(value = "/{postId}")
    @ResponseStatus(HttpStatus.FOUND)
    public final void deletePhoto(@PathVariable("id") Integer id) {
        LOGGER.debug("deletePost({})", id);
        Post post = postService.getPostById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == post.getUser()) {
            postService.deletePostById(id);
        }
    }
}

