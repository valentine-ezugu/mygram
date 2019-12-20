package com.valentine.service.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "amazon.s3")
public class AmazonS3Properties {

    private String region;
    private String endpoint;
    private String domain;
    private String bucketName;
    private String accessKey;
    private String secretKey;

}
