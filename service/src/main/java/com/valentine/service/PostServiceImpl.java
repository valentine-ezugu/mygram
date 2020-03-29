package com.valentine.service;

import com.valentine.dao.PostRepository;
import com.valentine.model.Photo;
import com.valentine.model.Post;
import com.valentine.model.User;
import com.valentine.service.exception.MyValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private AwsFileStorage fileStorage;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post savePost(MultipartFile file, String caption) throws IOException {
        checkIfImageElseThrowError(file);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = prepareArtifact(fileStorage, file, user, true);
        post.setCaption(caption);
        return postRepository.save(post);
    }

    @Override
    public void deletePostById(Integer id) throws DataAccessException {
        deletePostFromStorage(this.fileStorage, postRepository.getById(id));
        postRepository.deleteById(id);

    }

    @Override
    public Post getPostById(Integer id) throws DataAccessException {
        return null;
    }

    @Override
    public URL getPostDownloadURL(Integer id) throws DataAccessException {
        return null;
    }

    @Override
    public void deletePost(Post post) throws DataAccessException {

    }

    @Override
    public void deletePostFromStorage(AwsFileStorage fileStorage, Post post) {
        String objectId = fileStorage.extractObjectIdFromResourceUrl(post.getImage_path());
        fileStorage.deleteFile(objectId);
    }

    @Override
    public Post prepareArtifact(AwsFileStorage fileStorage, MultipartFile file, User currentUser, boolean isPublic) throws IOException {
        Post post = new Post();
        String url = fileStorage.saveFile(currentUser, file, file.getOriginalFilename());
        post.setInitialName(file.getOriginalFilename());
        post.setImage_path(url);
        post.setImage_size((int) (file.getSize()));
        post.setDateCreated(LocalDateTime.now());
        post.setUser(currentUser);
        return post;
    }

    private void checkIfImageElseThrowError(MultipartFile file) {
        String mimeType = file.getContentType();
        String type = mimeType.split("/")[0];
        if ( !(type.equalsIgnoreCase("image") || type.equalsIgnoreCase("video")) ) {
            throw new MyValidationException("image_or_video");
        }
    }


}
