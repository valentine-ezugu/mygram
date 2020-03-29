package com.valentine.service;

import com.valentine.model.Post;
import com.valentine.model.Video;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

public interface VideoService extends ArtifactPrep<Video> {


    Video savePost(MultipartFile file, String caption) throws IOException;

    void deleteVideoById(Integer id) throws DataAccessException;

    Post getVideotById(Integer id) throws DataAccessException;

    URL getVideoDownloadURL(Integer id) throws DataAccessException;

    void deleteVideo(Video video) throws DataAccessException;

    void deletePhotoFromStorage(AwsFileStorage fileStorage, Video video);

    static URL getDownloadUrl(AwsFileStorage fileStorage, Video video) {
        String objectId = fileStorage.extractObjectIdFromResourceUrl(video.getImage_path());
        return fileStorage.getPreSignedFileDownloadUrl(objectId);
    }

}
