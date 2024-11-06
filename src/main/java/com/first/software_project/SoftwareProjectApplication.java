package com.first.software_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = "com.first.software_project.model")
@EnableJpaRepositories(basePackages = "com.first.software_project.repository")
public class SoftwareProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(SoftwareProjectApplication.class, args);
	}

}
