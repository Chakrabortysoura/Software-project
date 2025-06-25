package com.software.room_allocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.software.room_allocation")
@EnableJpaRepositories("com.software.room_allocation.repository")
@EntityScan("com.software.room_allocation.model")
public class RoomAllocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomAllocationApplication.class, args);
	}

}
