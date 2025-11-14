package com.example.neurozen_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NeurozenPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeurozenPlatformApplication.class, args);
	}

}
