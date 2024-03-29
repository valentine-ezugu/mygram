/*
package com.valentine.utility.listner;

import com.valentine.model.Photo;
import com.valentine.service.AwsFileStorage;
import com.valentine.service.FileStorageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PreRemove;

public class PhotoListener {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private AwsFileStorage fileStorage;

    @PreRemove
    public void deleteRealFile(Photo photo) {
        Assert.notNull(photo.getImage_path(), "url should be represented");

        try {
            FileStorageHelper.deletePhoto(fileStorage, photo);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "sorry, can't delete file", ex);
        }
    }
}
*/
