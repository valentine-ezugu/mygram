package com.valentine.gram.controller;

import com.valentine.model.Photo;
import com.valentine.model.User;
import com.valentine.service.AwsFileStorage;
import com.valentine.service.FileStorageHelper;
import com.valentine.service.PhotoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@CrossOrigin
@RequestMapping(value = "/api")
@RestController
public class PostController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private PhotoService photoService;

    @Autowired
    AwsFileStorage awsFileStorage;

    @Autowired
    FileStorageHelper fileStorageHelper;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public Photo upload(@RequestParam("file") MultipartFile file ) throws IOException {
        LOGGER.debug("upload({})", file);
        return photoService.savePhoto(file);
    }

    @DeleteMapping(value = "/post/{postId}")
    @ResponseStatus(HttpStatus.FOUND)
    public final void deletePost(@PathVariable("postId") Integer postId) {
        LOGGER.debug("deletePost({})", postId);
        Photo photo = photoService.getPhotoById(postId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == photo.getUser()) {
            fileStorageHelper.deletePhoto(awsFileStorage, photo);
            photoService.deletePhotoById(postId);
        }
    }
}

