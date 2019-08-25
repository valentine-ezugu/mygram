package com.valentine.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.valentine.model.User;
import com.valentine.service.properties.AmazonS3Properties;
import com.valentine.service.exception.MyFileStorageException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
public class AwsS3FileStorage implements AwsFileStorage {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    private AmazonS3 s3client;

    private AmazonS3Properties s3Properties;

    private Date getExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60; //1 min
        expiration.setTime(expTimeMillis);
        return expiration;
    }

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(s3Properties.getAccessKey(),s3Properties.getSecretKey());
        this.s3client = AmazonS3ClientBuilder
            .standard()
            .withRegion(s3Properties.getRegion())
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .build();

    }

    @Override
    public void deleteFile(String objectId) throws MyFileStorageException {
        try {
            s3client.deleteObject(new DeleteObjectRequest(s3Properties.getBucketName(), objectId));
        } catch (RuntimeException ex) {
            LOGGER.error("delete error: {}", ex);
            throw new MyFileStorageException("delete error", ex);
        }
    }

    @Override
    public String saveFile(User currentUser, MultipartFile file , String initialName) throws IOException {
        try {
            String objectId = generateObjectId(currentUser, initialName);
            uploadToBucket(file, objectId);
            return constructResourceUrl(objectId);
        } catch (RuntimeException ex) {
            LOGGER.error("register error: {}", ex);
            throw new MyFileStorageException("register error", ex);
        }
    }

    private String generateObjectId(User currentUser, String initialName) {
        return currentUser.getUsername() + '/' + new Date().getTime() + "-" + initialName.replace(" ", "_");

    }

    private void uploadToBucket(MultipartFile file, String fileName) throws IOException {
        byte[] bytes = file.getBytes();
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(bytes.length);

        InputStream inputStream = new ByteArrayInputStream(bytes);
        s3client.putObject(new PutObjectRequest(s3Properties.getBucketName(), fileName, inputStream, meta)
            .withCannedAcl(CannedAccessControlList.PublicRead));

//        CannedAccessControlList cannedAccessControlList = isPublic ?
//            CannedAccessControlList.PublicRead :
//            CannedAccessControlList.BucketOwnerFullControl;
//
//        s3client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, meta)
//            .withCannedAcl(cannedAccessControlList));
    }


    @Override
    public URL getPreSignedFileDownloadUrl(String objectId) {
        try {
            Date expiration = getExpiration();
            return s3client.generatePresignedUrl(new GeneratePresignedUrlRequest(s3Properties.getBucketName(), objectId)
                .withExpiration(expiration));
        } catch (RuntimeException ex) {
            LOGGER.error("error getting pre-signed file download url: {}", ex);
            throw new MyFileStorageException("error getting pre-signed file download url", ex);
        }
    }

    @Override
    public String extractObjectIdFromResourceUrl(String url) {
        return url.replace(s3Properties.getDomain(), "");
    }

    private String constructResourceUrl(String objectId) {
        return s3Properties.getDomain() + "/" + objectId;
    }

    public void cleanBucket() {
        try {
            LOGGER.info(" - removing objects from bucket");
            ObjectListing object_listing = s3client.listObjects(s3Properties.getBucketName());
            while (true) {
                for (S3ObjectSummary summary : object_listing.getObjectSummaries()) {
                    s3client.deleteObject(s3Properties.getBucketName(), summary.getKey());
                }

                // more object_listing to retrieve?
                if (object_listing.isTruncated()) {
                    object_listing = s3client.listNextBatchOfObjects(object_listing);
                } else {
                    break;
                }
            }

            LOGGER.info(" - removing versions from bucket");
            VersionListing version_listing = s3client.listVersions(
                new ListVersionsRequest().withBucketName(s3Properties.getBucketName()));
            while (true) {
                for (S3VersionSummary vs : version_listing.getVersionSummaries()) {
                    s3client.deleteVersion(
                        s3Properties.getBucketName(), vs.getKey(), vs.getVersionId());
                }

                if (version_listing.isTruncated()) {
                    version_listing = s3client.listNextBatchOfVersions(
                        version_listing);
                } else {
                    break;
                }
            }
        } catch (RuntimeException ex) {
            LOGGER.error("cleaning bucket error: {}", ex);
        }
    }
}
