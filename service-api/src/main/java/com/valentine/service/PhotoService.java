package com.valentine.service;

import com.valentine.model.Photo;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

public interface PhotoService {

    Photo savePhoto(MultipartFile file) throws IOException;

    void deletePhotoById(Integer id) throws DataAccessException;

    Photo getPhotoById(Integer id) throws DataAccessException;

    URL getPhotoDownloadURL(Integer id) throws DataAccessException;

    void deleteArtifact(Photo photo) throws DataAccessException;
}
