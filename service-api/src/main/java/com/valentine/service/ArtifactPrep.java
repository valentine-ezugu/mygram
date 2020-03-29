package com.valentine.service;

import com.valentine.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ArtifactPrep<T> {

    T prepareArtifact(AwsFileStorage fileStorage, MultipartFile file, User currentUser, boolean isPublic) throws IOException;

}
