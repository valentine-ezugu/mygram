package com.valentine.service;


import com.valentine.model.Post;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URL;

public interface PostService extends ArtifactPrep<Post> {

    Post savePost(MultipartFile file, String caption) throws IOException;

    void deletePostById(Integer id) throws DataAccessException;

    Post getPostById(Integer id) throws DataAccessException;

    URL getPostDownloadURL(Integer id) throws DataAccessException;

    void deletePost(Post post) throws DataAccessException;

    void deletePostFromStorage(AwsFileStorage fileStorage, Post post);

    static URL getDownloadUrl(AwsFileStorage fileStorage, Post post) {
        String objectId = fileStorage.extractObjectIdFromResourceUrl(post.getImage_path());
        return fileStorage.getPreSignedFileDownloadUrl(objectId);
    }

}
