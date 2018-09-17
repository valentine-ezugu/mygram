package com.valentine.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void store(MultipartFile multipartFile);

    Resource loadFile(String filename);

    //remove all uploaded files
    void deleteAll();

    // create upload directory
    void init();

    public Stream<Path> loadFiles();

}
