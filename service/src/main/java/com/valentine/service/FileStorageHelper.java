package com.valentine.service;

import com.valentine.model.Photo;
import com.valentine.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

@Service
public class FileStorageHelper  implements PrepareArtifact{

    public Photo preparePhoto(AwsFileStorage fileStorage, MultipartFile file, User currentUser, boolean isPublic) throws IOException {
        Photo photo = new Photo();
        String url = fileStorage.saveFile(currentUser, file, file.getOriginalFilename());
        photo.setInitialName(file.getOriginalFilename());
        photo.setImage_path(url);
        photo.setImage_size((int)(file.getSize()));
        photo.setDate_created(LocalDate.now());
        photo.setUser(currentUser);
        return photo;
    }

    public void deletePhoto(AwsFileStorage fileStorage, Photo photo) {
        String objectId = fileStorage.extractObjectIdFromResourceUrl(photo.getImage_path());
        fileStorage.deleteFile(objectId);
    }


}
