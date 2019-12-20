package com.valentine.service;

import com.valentine.model.User;
import com.valentine.service.exception.MyFileStorageException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

public interface AwsFileStorage {

    /**
     * @param objectId of stored file to delete
     */
    void deleteFile(String objectId) throws MyFileStorageException;
    /**
     * @return location
     */
    String saveFile(User currentUser, MultipartFile file, String initialName) throws IOException;

    /**
     * @param objectId of stored object
     * @return pre-sighed url of stored object
     */
    URL getPreSignedFileDownloadUrl(String objectId) throws MyFileStorageException;

    String extractObjectIdFromResourceUrl(String url);

}
