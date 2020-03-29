package com.valentine.service;

import com.valentine.model.Photo;
import com.valentine.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

public interface PhotoService extends ArtifactPrep<Photo> {

    Photo savePhoto(MultipartFile file) throws IOException;

    void deletePhotoById(Integer id) throws DataAccessException;

    Photo getPhotoById(Integer id) throws DataAccessException;

    URL getPhotoDownloadURL(Integer id) throws DataAccessException;

    void deletePhoto(Photo photo) throws DataAccessException;

    void deletePhotoFromStorage(AwsFileStorage fileStorage, Photo photo);

    static URL getDownloadUrl(AwsFileStorage fileStorage, Photo photo) {
        String objectId = fileStorage.extractObjectIdFromResourceUrl(photo.getImage_path());
        return fileStorage.getPreSignedFileDownloadUrl(objectId);
    }

}
