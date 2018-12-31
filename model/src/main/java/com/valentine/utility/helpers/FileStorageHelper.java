package com.valentine.utility.helpers;

import com.valentine.model.Photo;
import java.net.URL;
import com.valentine.model.User;

import com.valentine.utility.FileStorage;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


public class FileStorageHelper {

    public static Photo preparePhoto(FileStorage fileStorage, MultipartFile file, User currentUser, boolean isPublic) throws IOException {
        Photo photo = new Photo();
        String url = fileStorage.saveFile(currentUser, IOUtils.toByteArray(file.getInputStream()), file.getOriginalFilename(), isPublic);
        photo.setInitialName(file.getOriginalFilename());
        photo.setImage_path(url);
        return photo;
    }

    public static void deletePhoto(FileStorage fileStorage, Photo photo) {
        String objectId = fileStorage.extractObjectIdFromResourceUrl(photo.getImage_path());
        fileStorage.deleteFile(objectId);
    }

    public static URL getDownloadUrl(FileStorage fileStorage, Photo photo) {
        String objectId = fileStorage.extractObjectIdFromResourceUrl(photo.getImage_path());
        return fileStorage.getPreSignedFileDownloadUrl(objectId);
    }

}
