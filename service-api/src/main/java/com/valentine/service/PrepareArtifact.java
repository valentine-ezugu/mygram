package com.valentine.service;

import com.valentine.model.Photo;
import com.valentine.model.User;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URL;


public interface PrepareArtifact {

    Photo preparePhoto(AwsFileStorage fileStorage, MultipartFile file, User currentUser, boolean isPublic) throws IOException;

    void deletePhoto(AwsFileStorage fileStorage, Photo photo);

    static URL getDownloadUrl(AwsFileStorage fileStorage, Photo photo) {
        String objectId = fileStorage.extractObjectIdFromResourceUrl(photo.getImage_path());
        return fileStorage.getPreSignedFileDownloadUrl(objectId);
    }

}