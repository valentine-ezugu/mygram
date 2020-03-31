package com.valentine.gram.controller;

import com.valentine.model.Photo;
import com.valentine.model.User;
import com.valentine.service.AwsFileStorage;
import com.valentine.service.PhotoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@CrossOrigin
@RestController("/api/photo")
public class PhotoController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private PhotoService photoService;

    @Autowired
    AwsFileStorage awsFileStorage;


    @GetMapping("/{" + "photoId" + "}/download-url")
    public ResponseEntity<String> getArtifactDownloadUrl(@PathVariable("photoId") Integer photoId) {
        LOGGER.debug("getArtifactDownloadUrl ({})", photoId);
        URL downloadUrl = photoService.getPhotoDownloadURL(photoId);
        return ResponseEntity.ok().body(downloadUrl.toString());
    }

    @GetMapping("photoId")
    public ResponseEntity<Photo> getPhoto(@PathVariable("photoId") Integer photoId) {
        LOGGER.debug("getPhoto({})", photoId);
        return ResponseEntity.ok().body(photoService.getPhotoById(photoId));
    }


    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public Photo upload(@RequestParam("file") MultipartFile file ) throws IOException {
        LOGGER.debug("upload({})", file);
        return photoService.savePhoto(file);
    }

    @DeleteMapping(value = "/{photoId}")
    @ResponseStatus(HttpStatus.FOUND)
    public final void deletePhoto(@PathVariable("id") Integer id) {
        LOGGER.debug("deletePhoto({})", id);
        Photo photo = photoService.getPhotoById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == photo.getUser()) {
            photoService.deletePhotoFromStorage(awsFileStorage, photo);
            photoService.deletePhotoById(id);
        }
    }
}
