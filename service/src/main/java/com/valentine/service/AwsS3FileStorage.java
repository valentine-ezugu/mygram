package com.valentine.utility;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.valentine.model.User;
import com.valentine.utility.exception.MyFileStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;


public class AwsS3FileStorage implements FileStorage {

    private static final Logger logger = LoggerFactory.getLogger(AwsS3FileStorage.class);

    private AmazonS3 s3client;

    @Value("${amazon.s3.region}")
    private String region;
    @Value("${amazon.s3.endpoint}")
    private String endpoint;
    @Value("${amazon.s3.domain}")
    private String domain;
    @Value("${amazon.s3.bucketName}")
    private String bucketName;
    @Value("${amazon.s3.accessKey}")
    private String accessKey;
    @Value("${amazon.s3.secretKey}")
    private String secretKey;

    private Date getExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60; //1 min
        expiration.setTime(expTimeMillis);
        return expiration;
    }

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder
            .standard()
            .withRegion(region)
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .build();
    }

    @Override
    public void deleteFile(String objectId) throws MyFileStorageException {
        try {
            s3client.deleteObject(new DeleteObjectRequest(bucketName, objectId));
        } catch (RuntimeException ex) {
            logger.error("delete error: {}", ex);
            throw new MyFileStorageException("delete error", ex);
        }
    }

    @Override
    public String saveFile(User currentUser, byte[] bytes, String initialName, boolean isPublic) {
        try {
            String objectId = generateObjectId(currentUser, initialName);
            uploadToBucket(bytes, objectId, isPublic);
            return constructResourceUrl(objectId);
        } catch (RuntimeException ex) {
            logger.error("save error: {}", ex);
            throw new MyFileStorageException("save error", ex);
        }
    }

    private String generateObjectId(User currentUser, String initialName) {
        return currentUser.getUsername() + '/' + new Date().getTime() + "-" + initialName.replace(" ", "_");
    }

    private void uploadToBucket(byte[] bytes, String fileName, boolean isPublic) {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(bytes.length);

        InputStream inputStream = new ByteArrayInputStream(bytes);

        CannedAccessControlList cannedAccessControlList = isPublic ?
            CannedAccessControlList.PublicRead :
            CannedAccessControlList.BucketOwnerFullControl;

        s3client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, meta)
            .withCannedAcl(cannedAccessControlList));
    }


    @Override
    public URL getPreSignedFileDownloadUrl(String objectId) {
        try {
            Date expiration = getExpiration();
            return s3client.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, objectId)
                .withExpiration(expiration));
        } catch (RuntimeException ex) {
            logger.error("error getting pre-signed file download url: {}", ex);
            throw new MyFileStorageException("error getting pre-signed file download url", ex);
        }
    }

    @Override
    public String extractObjectIdFromResourceUrl(String url) {
        return url.replace(domain, "");
    }

    private String constructResourceUrl(String objectId) {
        return domain + "/" + objectId;
    }

    public void cleanBucket() {
        try {
            logger.info(" - removing objects from bucket");
            ObjectListing object_listing = s3client.listObjects(bucketName);
            while (true) {
                for (S3ObjectSummary summary : object_listing.getObjectSummaries()) {
                    s3client.deleteObject(bucketName, summary.getKey());
                }

                // more object_listing to retrieve?
                if (object_listing.isTruncated()) {
                    object_listing = s3client.listNextBatchOfObjects(object_listing);
                } else {
                    break;
                }
            }

            logger.info(" - removing versions from bucket");
            VersionListing version_listing = s3client.listVersions(
                new ListVersionsRequest().withBucketName(bucketName));
            while (true) {
                for (S3VersionSummary vs : version_listing.getVersionSummaries()) {
                    s3client.deleteVersion(
                        bucketName, vs.getKey(), vs.getVersionId());
                }

                if (version_listing.isTruncated()) {
                    version_listing = s3client.listNextBatchOfVersions(
                        version_listing);
                } else {
                    break;
                }
            }
        } catch (RuntimeException ex) {
            logger.error("clean error: {}", ex);
        }
    }
}
