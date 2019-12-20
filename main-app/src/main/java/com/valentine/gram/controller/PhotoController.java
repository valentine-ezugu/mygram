package com.valentine.gram.controller;

import com.valentine.model.Photo;
import com.valentine.service.PhotoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.net.URL; 

@CrossOrigin
@RestController("/api/v1/photos")
public class PhotoController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private PhotoService photoService;

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

}
