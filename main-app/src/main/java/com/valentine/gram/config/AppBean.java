package com.valentine.gram.config;

import com.amazonaws.services.s3.AmazonS3;
import com.valentine.service.properties.AmazonS3Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.MultipartConfigElement;

@Configuration
public class AppBean {

    @Bean
    public AmazonS3 s3client(@Autowired AmazonS3Properties s3Properties) {
        AWSCredentials credentials = new BasicAWSCredentials(s3Properties.getAccessKey(), s3Properties.getSecretKey());
        return AmazonS3ClientBuilder
            .standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3Properties.getEndpoint(), s3Properties.getRegion()))
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .build();
    }

}

