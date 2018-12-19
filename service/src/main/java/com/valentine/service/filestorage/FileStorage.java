package com.valentine.utility;


import com.valentine.model.User;
import com.valentine.utility.exception.MyFileStorageException;

import java.net.URL;

public interface FileStorage {

    /**
     * @param objectId of stored file to delete
     */
    void deleteFile(String objectId) throws MyFileStorageException;

    /**
     * @return location
     */
    String saveFile(User currentUser, byte[] bytes, String initialName, boolean isPublic) throws MyFileStorageException;

    /**
     * @param objectId of stored object
     * @return pre-sighed url of stored object
     */
    URL getPreSignedFileDownloadUrl(String objectId) throws MyFileStorageException;

    String extractObjectIdFromResourceUrl(String url);

}
