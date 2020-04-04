package com.valentine.gram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EntityScan(basePackages = {"com.valentine.model","com.valentine.messenger"})
@SpringBootApplication(scanBasePackages = {"com.valentine.gram",
	"com.valentine.service", "com.valentine.dao" })
@EnableJpaRepositories("com.valentine.dao")
@EnableTransactionManagement
@EnableConfigurationProperties
public class MainApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainApp.class);
    }
}
