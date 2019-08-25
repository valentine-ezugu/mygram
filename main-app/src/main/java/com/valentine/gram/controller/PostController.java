package com.valentine.gram.controller;

import com.valentine.model.Photo;
import com.valentine.model.User;
import com.valentine.service.PhotoService;
import com.valentine.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RequestMapping(value = "/api")
@RestController
public class PostController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public Photo submitPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        LOGGER.debug("submitPhoto({})", file);
         return photoService.savePhoto(file);
    }

    @RequestMapping(method = GET, value = "/user/all")
    public List<User> loadAll() {
        return this.userService.findAll();
    }

    @RequestMapping(method = GET, value = "/user/jak")
    public List<User> loadddAll() {
        return null;
    }

}

