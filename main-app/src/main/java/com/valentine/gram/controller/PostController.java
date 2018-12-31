package com.valentine.gram.controller;


import com.valentine.gram.errorhandling.ImageUploadException;
import com.valentine.model.User;
import com.valentine.service.UserService;
import org.jets3t.service.S3Service;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@CrossOrigin
@RestController
public class PostController {

    @Autowired
    private UserService userService;


    private final String AWS_ACCESS_KEY_ID = "AKIAJHNP47TUYQMXS5WA";

    private final String AWS_SECRET_KEY = "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";



   @PostMapping
   void newPost(User currentUser, MultipartFile file)
   {

    }

    @PostMapping(value = "/upload")
    public void submit(@RequestParam("photo") MultipartFile file) throws ImageUploadException {
        try {
            AWSCredentials awsCredentials = new AWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
            S3Service s3 = new RestS3Service(awsCredentials);

            S3Bucket bucket = s3.getBucket("viagramfiles");
            S3Object imageObject = new S3Object(file.getOriginalFilename());

            imageObject.setDataInputStream(file.getInputStream());

            imageObject.setContentLength(file.getSize());
            imageObject.setContentType(file.getContentType());
            AccessControlList acl = new AccessControlList();

            acl.setOwner(bucket.getOwner());
            acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
            imageObject.setAcl(acl);

            s3.putObject(bucket, imageObject);

        } catch (Exception e) {
            throw new ImageUploadException("Unable to save image", e);
        }
    }

}

