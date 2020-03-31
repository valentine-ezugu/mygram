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
import com.zaxxer.hikari.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.Properties;

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


    @Value("${spring.datasource.url}")
    private String dbUrl;

    Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("spring.jpa.properties.hibernate.hbm2ddl.auto", "create");
        return properties;
    }

    @Bean
    @Profile("prod")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setPassword("50b0564e39f8f4883c3ba57e8ea8b51bd720bde1259b013f7819bcdcccfbbad8");
        config.setUsername("olobmpqjjytfbn");
        config.setDriverClassName("org.postgresql.Driver");
        config.setDataSourceProperties(properties());
        return new HikariDataSource(config);
    }
}

