package com.valentine.service;

import com.valentine.model.Photo;
import com.valentine.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URL;

@Service
public class FileStorageHelper {

    public Photo preparePhoto(AwsFileStorage fileStorage, MultipartFile file, User currentUser, boolean isPublic) throws IOException {
        Photo photo = new Photo();
        String url = fileStorage.saveFile(currentUser, file, file.getOriginalFilename());
        photo.setInitialName(file.getOriginalFilename());
        photo.setImage_path(url);
        return photo;
    }

    public static void deletePhoto(AwsFileStorage fileStorage, Photo photo) {
        String objectId = fileStorage.extractObjectIdFromResourceUrl(photo.getImage_path());
        fileStorage.deleteFile(objectId);
    }

    public static URL getDownloadUrl(AwsFileStorage fileStorage, Photo photo) {
        String objectId = fileStorage.extractObjectIdFromResourceUrl(photo.getImage_path());
        return fileStorage.getPreSignedFileDownloadUrl(objectId);
    }

}
